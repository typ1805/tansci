package com.tansci.service.impl.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tansci.common.constant.Constants;
import com.tansci.common.exception.BusinessException;
import com.tansci.config.AuthorizedConfig;
import com.tansci.domain.system.SysUser;
import com.tansci.domain.system.SysUserRole;
import com.tansci.domain.system.vo.AuthorizedVo;
import com.tansci.service.impl.message.WebSocketServer;
import com.tansci.service.system.AuthorizedService;
import com.tansci.service.system.SysUserRoleService;
import com.tansci.service.system.SysUserService;
import com.tansci.utils.JwtTokenUtils;
import com.tansci.utils.QRCodeUtils;
import com.tansci.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @ClassName： AuthorizedServiceImpl.java
 * @ClassPath： com.tansci.service.impl.system.AuthorizedServiceImpl.java
 * @Description： 三方授权
 * @Author： tanyp
 * @Date： 2022/3/1 10:53
 **/
@Slf4j
@Service
public class AuthorizedServiceImpl implements AuthorizedService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public AuthorizedVo wxLogin() {
        if (!AuthorizedConfig.WX_APP_ID.startsWith("wx")){
            throw new BusinessException("无效的微信配置信息！");
        }
        // 微信开放平台授权baseUrl
        String baseUrl = AuthorizedConfig.WX_BASE_URL + "?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=%s#wechat_redirect";

        // 回调地址
        String redirectUrl = AuthorizedConfig.WX_REDIRECT_URL;
        try {
            // url编码
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new BusinessException(e.getMessage());
        }

        // 防止csrf攻击（跨站请求伪造攻击）,一般情况下会使用一个随机数
        String state = UUIDUtils.getUUID();
        // 生成qrcodeUrl
        String qrcodeUrl = String.format(baseUrl, AuthorizedConfig.WX_APP_ID, redirectUrl, state);
        log.info("========生成qrcodeUrl:{}===========", qrcodeUrl);
        String qrcode = QRCodeUtils.crateQRCode(qrcodeUrl, null, null);
        return AuthorizedVo.builder().state(state).qrcode(qrcode).build();
    }

    @Override
    public void wxCallback(String code, String state) {
        AuthorizedVo authorizedVo = AuthorizedVo.builder().msg("登录成功").status(200).build();

        // 获取 access_token
        String accessTokenUrl = AuthorizedConfig.WX_BASE_ACCESS_TOKEN_URL +
                "?appid=" + AuthorizedConfig.WX_APP_ID + "&secret=" + AuthorizedConfig.WX_APP_SECRET + "&code=" + code + "&grant_type=authorization_code";
        log.info("=======获取 access_token URL：{}==============", accessTokenUrl);

        String tokenResult = restTemplate.getForObject(accessTokenUrl, String.class);
        JSONObject tokenJson = JSON.parseObject(tokenResult);
        log.info("========获取 access_token 结果========：{}", tokenJson);

        if (Objects.isNull(tokenResult) || Objects.isNull(tokenJson.get("openid"))) {
            authorizedVo.setMsg("获取access_token失败");
            authorizedVo.setStatus(500);
        }
        String accessToken = String.valueOf(tokenJson.get("access_token"));
        String openid = String.valueOf(tokenJson.get("openid"));

        // 查询数据库当前用用户是否曾经使用过微信登录
        SysUser user = sysUserService.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getOpenId, openid));
        if (Objects.isNull(user)) {
            log.info("==========新用户注册============");

            // 访问微信的资源服务器，获取用户信息
            String baseUserInfoUrl = AuthorizedConfig.WX_BASE_USER_INFO_URL + "?access_token=" + accessToken + "&openid=" + openid;
            String userInfo = restTemplate.getForObject(baseUserInfoUrl, String.class);
            JSONObject userJson = JSON.parseObject(userInfo);
            log.info("========获取微信用户信息结果========：{}", userJson);

            if (Objects.isNull(userInfo) || Objects.isNull(userJson.get("nickname"))) {
                authorizedVo.setMsg("获取微信用户信息失败");
                authorizedVo.setStatus(500);
            }

            String nickname = String.valueOf(userJson.get("nickname"));
            try {
                nickname = new String(nickname.getBytes("ISO-8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                log.error("=====nickname 转码失败======={}", e);
            }

            // 向数据库中插入一条记录
            user = SysUser.builder()
                    .username(UUIDUtils.getUUID(15))
                    .nickname(nickname)
                    .openId(openid)
                    .type(2)
                    .password(new BCryptPasswordEncoder().encode("wx123456"))
                    .updateTime(LocalDateTime.now())
                    .createTime(LocalDateTime.now())
                    .delFlag(Constants.NOT_DEL_FALG)
                    .remarks("微信扫描注册用户")
                    .build();
            sysUserService.save(user);
            // 默认用户角色为普通用户
            sysUserRoleService.save(SysUserRole.builder().roleId(2).userId(user.getId()).build());
        }

        // 生成token
        if (Objects.equals(200, authorizedVo.getStatus())) {
            authorizedVo.setLoginTime(LocalDateTime.now());
            authorizedVo.setUsername(user.getUsername());
            authorizedVo.setNickname(user.getNickname());
            authorizedVo.setToken(JwtTokenUtils.createToken(SysUser.builder().id(user.getId()).username(user.getUsername()).password(user.getPassword()).type(user.getType()).role("2").build(), true));
        }
        try {
            // 发送通知
            log.info("===发送通知====id:{}=====:{}", state, JSON.toJSONString(authorizedVo));
            WebSocketServer.sendMessage(state, JSON.toJSONString(authorizedVo));
        } catch (IOException e) {
            log.error("=======发送通知异常=========={}", e);
        }
    }

}

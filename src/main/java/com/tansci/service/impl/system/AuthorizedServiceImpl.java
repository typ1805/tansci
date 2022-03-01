package com.tansci.service.impl.system;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.gson.Gson;
import com.tansci.common.exception.BusinessException;
import com.tansci.config.AuthorizedConfig;
import com.tansci.domain.system.SysUser;
import com.tansci.service.system.AuthorizedService;
import com.tansci.service.system.SysUserService;
import com.tansci.utils.QRCodeUtils;
import com.tansci.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
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

    @Override
    public String wxLogin() {
        // 微信开放平台授权baseUrl
        String baseUrl = AuthorizedConfig.WX_BASE_URL +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

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
        return QRCodeUtils.crateQRCode(qrcodeUrl, null, null);
    }

    @Override
    public String wxCallback(String code, String state) {
        //向认证服务器发送请求换取access_token
        String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";

        String accessTokenUrl = String.format(baseAccessTokenUrl, AuthorizedConfig.WX_APP_ID, AuthorizedConfig.WX_APP_SECRET, code);
        String result = null;
        try {
            result = restTemplate.getForObject(accessTokenUrl, String.class);
            System.out.println("accessToken=============" + result);
        } catch (Exception e) {
            throw new BusinessException("获取access_token失败");
        }

        // 解析json字符串
        Gson gson = new Gson();
        HashMap map = gson.fromJson(result, HashMap.class);
        String accessToken = (String) map.get("access_token");
        String openid = (String) map.get("openid");

        // 查询数据库当前用用户是否曾经使用过微信登录
        SysUser user = sysUserService.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getOpenId, openid));
        if (Objects.isNull(user)) {
            log.info("==========新用户注册============");

            // 访问微信的资源服务器，获取用户信息
            String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";
            String userInfoUrl = String.format(baseUserInfoUrl, accessToken, openid);
            String resultUserInfo = null;
            try {
                resultUserInfo = restTemplate.getForObject(userInfoUrl, String.class);
                System.out.println("resultUserInfo==========" + resultUserInfo);
            } catch (Exception e) {
                throw new BusinessException("获取用户信息失败");
            }

            // 解析json
            HashMap<String, Object> mapUserInfo = gson.fromJson(resultUserInfo, HashMap.class);
            String nickname = (String) mapUserInfo.get("nickname");
            String headimgurl = (String) mapUserInfo.get("headimgurl");

            // 向数据库中插入一条记录
            sysUserService.save(
                    SysUser.builder()
                            .nickname(nickname)
                            .openId(openid)
                            .build()
            );
        }
        return null;
    }

}

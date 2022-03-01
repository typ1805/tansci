package com.tansci.config;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

/**
 * @ClassName： AuthorizedConfig.java
 * @ClassPath： com.tansci.config.AuthorizedConfig.java
 * @Description： 三方授权配置信息
 * @Author： tanyp
 * @Date： 2022/3/1 10:01
 **/
@Data
public class AuthorizedConfig implements InitializingBean {

    /**
     * 微信配置
     */

    // appid
    @Value("${authorized.wechat.app_id}")
    private String wxAppId;

    // appsecret
    @Value("${authorized.wechat.app_id}")
    private String wxAppSecret;

    // appsecret
    @Value("${authorized.wechat.base_url}")
    private String wxBaseUrl;

    // 重定向ur
    @Value("${authorized.wechat.app_id}")
    private String wxRedirectUrl;

    public static String WX_APP_ID;
    public static String WX_APP_SECRET;
    public static String WX_BASE_URL;
    public static String WX_REDIRECT_URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        WX_APP_ID = wxAppId;
        WX_APP_SECRET = wxAppSecret;
        WX_BASE_URL = wxRedirectUrl;
        WX_REDIRECT_URL = wxRedirectUrl;
    }

}

package com.tansci.config;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName： SmsConfig.java
 * @ClassPath： com.tansci.config.PaymentConfig.java
 * @Description： 支付配置
 * @Author： tanyp
 * @Date： 2022/03/28 10:41
 **/
@Data
@Component
public class PaymentConfig implements InitializingBean {

    /**
     * 微信配置
     */
    @Value("${pay.wechat.app-id}")
    private String wxAppId;

    // 商户号
    @Value("${pay.wechat.mch-id}")
    private String wxMchId;

    // API证书序列号
    @Value("${pay.wechat.mch-serial-no}")
    private String wxMchSerialNo;

    // apiV3秘钥
    @Value("${pay.wechat.apiv3-key}")
    private String wxApiv3Key;

    // 回调通知地址
    @Value("${pay.wechat.notify-url}")
    private String wxNnotifyUrl;

    // API证书私钥
    @Value("${pay.wechat.private-key}")
    private String wxPrivateKey;

    public static String WX_APP_ID;
    public static String WX_MCH_ID;
    public static String WX_MCH_SERIAL_NO;
    public static String WX_APIV3_KEY;
    public static String WX_NNOTIFY_URL;
    public static String WX_PRIVATE_KEY;

    /**
     * 支付宝配置
     */
    @Value("${pay.ali.app-id}")
    private String aliAppId;

    @Value("${pay.ali.authorized-url}")
    private String aliAuthorizedUrl;

    @Value("${pay.ali.format}")
    private String aliFormat;

    @Value("${pay.ali.charset}")
    private String aliCharset;

    @Value("${pay.ali.sign-type}")
    private String aliSignType;

    @Value("${pay.ali.notify-url}")
    private String aliNotifyUrl;

    @Value("${pay.ali.gateway-url}")
    private String aliGatewayUrl;

    @Value("${pay.ali.return-url}")
    private String aliReturnUrl;

    @Value("${pay.ali.private-key}")
    private String aliPrivateKey;

    @Value("${pay.ali.public-key}")
    private String aliPublicKey;

    public static String ALI_APP_ID;
    public static String ALI_AUTHORIZED_URL;
    public static String ALI_FORMAT;
    public static String ALI_CHARSET;
    public static String ALI_SIGN_TYPE;
    public static String ALI_NOTIFY_URL;
    public static String ALI_GATEWAY_URL;
    public static String ALI_RETURN_URL;
    public static String ALI_PRIVATE_KEY;
    public static String ALI_PUBLIC_KEY;

    @Override
    public void afterPropertiesSet() throws Exception {
        WX_APP_ID = wxAppId;
        WX_MCH_ID = wxMchId;
        WX_MCH_SERIAL_NO = wxMchSerialNo;
        WX_APIV3_KEY = wxApiv3Key;
        WX_NNOTIFY_URL = wxNnotifyUrl;
        WX_PRIVATE_KEY = wxPrivateKey;
        ALI_APP_ID = aliAppId;
        ALI_AUTHORIZED_URL = aliAuthorizedUrl;
        ALI_FORMAT = aliFormat;
        ALI_CHARSET = aliCharset;
        ALI_SIGN_TYPE = aliSignType;
        ALI_NOTIFY_URL = aliNotifyUrl;
        ALI_GATEWAY_URL = aliGatewayUrl;
        ALI_RETURN_URL = aliReturnUrl;
        ALI_PRIVATE_KEY = aliPrivateKey;
        ALI_PUBLIC_KEY = aliPublicKey;
    }

}

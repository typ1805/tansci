package com.tansci.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName： SmsConfig.java
 * @ClassPath： com.tansci.config.SmsConfig.java
 * @Description： 短信配置
 * @Author： tanyp
 * @Date： 2021/6/7 16:41
 **/
@Data
@Component
public class SmsConfig {

    @Value("${sms.access-id}")
    private String accessId;

    @Value("${sms.access-key}")
    private String accessKey;

    @Value("${sms.sign-name}")
    private String signName;

    @Value("${sms.endpoint}")
    private String endpoint;

}

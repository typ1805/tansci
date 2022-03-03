package com.tansci.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName： RestTemplateConfig.java
 * @ClassPath： com.tansci.config.RestTemplateConfig.java
 * @Description： RestTemplate配置
 * @Author： tanyp
 * @Date： 2022/2/28 11:17
 **/
@Component
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

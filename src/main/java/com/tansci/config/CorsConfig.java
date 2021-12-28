package com.tansci.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName： CorsConfig.java
 * @ClassPath： com.tansci.config.CorsConfig.java
 * @Description： 跨域配置
 * @Author： tanyp
 * @Date： 2021/10/22 15:26
 **/
@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路由
        registry.addMapping("/**")
                .allowedOriginPatterns("*") // 设置允许跨域请求的域名
                .allowCredentials(true) // 是否允许证书（cookies）
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS") // 设置允许的方法
                .maxAge(3600);  // 跨域允许时间
    }

}

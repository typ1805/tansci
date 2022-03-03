package com.tansci.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @ClassName： WebSocketConfig.java
 * @ClassPath： com.tansci.config.WebSocketConfig.java
 * @Description： Socket配置
 * @Author： tanyp
 * @Date： 2022/3/3 8:43
 **/
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}

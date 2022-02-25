package com.tansci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TansciApplication {

    public static void main(String[] args) {
        SpringApplication.run(TansciApplication.class, args);
    }

}

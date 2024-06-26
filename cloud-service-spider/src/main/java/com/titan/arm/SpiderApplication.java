package com.titan.arm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: nirunfeng
 * \* Date: 2024/6/26
 * \* Time: 10:57
 * \* Description:
 * \
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpiderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpiderApplication.class,args);
    }
}

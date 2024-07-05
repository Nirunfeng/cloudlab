package com.titan.arm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: nirunfeng
 * \* Date: 2024/7/5
 * \* Time: 14:49
 * \* Description:
 * \
 */

@SpringBootApplication
@EnableDiscoveryClient
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class,args);
    }
}

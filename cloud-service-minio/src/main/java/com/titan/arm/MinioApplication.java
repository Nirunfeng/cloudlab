package com.titan.arm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2024/10/27 15:32
 **/

@SpringBootApplication
@EnableDiscoveryClient
public class MinioApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinioApplication.class,args);
    }
}

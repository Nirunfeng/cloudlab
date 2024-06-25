package com.titan.arm.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: nirunfeng
 * \* Date: 2024/6/25
 * \* Time: 9:24
 * \* Description: ribbon+restTemplate
 * \
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced  //负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

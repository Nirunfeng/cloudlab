package com.titan.arm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:  这段配置告诉 Spring Boot 将 /files/** 映射到文件系统中的 uploadDir 目录。
 * @Date 2024/6/15 16:39
 **/

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${web.avatar.upload-path}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/files/**").addResourceLocations("file:" + uploadDir + "/");
    }
}

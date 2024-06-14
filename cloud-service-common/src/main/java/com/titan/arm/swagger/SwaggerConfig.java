package com.titan.arm.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2024/6/14 23:15
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 创建API应用 apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     */
    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                //接口说明信息
                .apiInfo(apiInfo())
                // 选择那些路径和api会生成document
                .pathMapping("/").select()
                // 对所有api进行监控
                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.titan.arm"))
               .build();
    }


    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("微服务接口文档")
                .contact(new Contact("nirunfeng","https://www.baidu.com","2570034697@qq.com"))
                .termsOfServiceUrl("https://www.baidu.com")
                .description("微服务接口文档")
                .version("1.0")
                .build();
    }
}

package com.pdsu.banmeng.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-21 19:10
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String groupName;

    /**
     * swagger 配置
     */
    @Bean
    public Docket webApiConfig(ApiInfo apiInfo) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pdsu.banmeng.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("毕业设计-代码共享社区")
                .description("本文档包含事务API")
                .version("1.0.0")
                .contact(new Contact("半梦Oo", "https://github.com/pyb1430501241", "1430501241@qq.com"))
                .build();
    }

}

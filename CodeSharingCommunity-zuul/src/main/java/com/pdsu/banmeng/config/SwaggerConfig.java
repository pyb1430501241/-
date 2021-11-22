package com.pdsu.banmeng.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-21 19:04
 */
@Configuration
@Primary
@EnableSwagger2
public class SwaggerConfig implements SwaggerResourcesProvider {

    @Value("${spring.application.name}")
    private String groupName;

    private final RouteLocator routeLocator;

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
                .description("本文档包含所有的API")
                .version("1.0.0")
                .contact(new Contact("半梦Oo", "https://github.com/pyb1430501241", "1430501241@qq.com"))
                .build();
    }

    public SwaggerConfig(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    //这个方法用来添加swagger的数据源
    @Override
    public List<SwaggerResource> get() {
        //在这里遍历的时候，可以排除掉敏感微服务的路由
        List<SwaggerResource> collect = routeLocator.getRoutes().stream()
                .filter(s -> !groupName.equals(s.getId()))
                .map(r -> swaggerResource(r.getId(), (r.getFullPath().
                        replace("**", "v2/api-docs") + "?group=" + r.getId()), "1.0.0"))
                .collect(Collectors.toList());
        collect.add(swaggerResource(groupName, "/v2/api-docs" + "?group=" + groupName, "1.0.0"));
        return collect;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setUrl(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

}

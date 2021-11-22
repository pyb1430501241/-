package com.pdsu.banmeng.config;

import com.pdsu.banmeng.utils.HttpUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-21 16:41
 */
@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")	// 允许跨域访问的路径
                        .allowedOrigins("http:localhost")	// 允许跨域访问的源
                        .allowedMethods("GET", "POST", "OPTIONS")
                        .maxAge(60)	// 预检间隔时间
                        .allowedHeaders("authorization") // 允许头部设置
                        .exposedHeaders()
                        .allowCredentials(Boolean.TRUE);	// 是否发送cookie
            }
        };
    }

}

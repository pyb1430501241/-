package com.pdsu.banmeng.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-20 20:09
 */
@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource(HikariConfig config) {
        return new HikariDataSource(config);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    public HikariConfig config() {
        return new HikariConfig();
    }

}

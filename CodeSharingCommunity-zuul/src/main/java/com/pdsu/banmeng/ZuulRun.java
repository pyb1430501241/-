package com.pdsu.banmeng;

import com.pdsu.banmeng.utils.HashUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-20 20:00
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@MapperScan("com.pdsu.banmeng.mapper")
public class ZuulRun {

    public static void main(String[] args) {
        SpringApplication.run(ZuulRun.class, args);
    }

}

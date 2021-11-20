package com.pdsu.banmeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-20 20:00
 */
@SpringCloudApplication
@EnableZuulServer
@MapperScan("com.pdsu.banmeng.mapper")
public class ZuulRun {

    public static void main(String[] args) {
        SpringApplication.run(ZuulRun.class, args);
    }

}

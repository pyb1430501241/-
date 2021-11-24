package com.pdsu.banmeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-20 20:00
 */
@SpringCloudApplication
@EnableZuulProxy
@MapperScan("com.pdsu.banmeng.mapper")
@EnableCaching
@EnableTransactionManagement
@EnableAsync
@EnableAspectJAutoProxy
public class ZuulRun {

    public static void main(String[] args) {
        SpringApplication.run(ZuulRun.class, args);
    }

}

package com.pdsu.banmeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-20 19:51
 */
@SpringCloudApplication
@MapperScan("com.pdsu.banmeng.mapper")
@EnableAspectJAutoProxy
@EnableCaching
@EnableAsync
@EnableTransactionManagement
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

}

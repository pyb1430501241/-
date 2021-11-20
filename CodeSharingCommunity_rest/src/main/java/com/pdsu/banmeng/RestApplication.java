package com.pdsu.banmeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-20 19:51
 */
@SpringCloudApplication
@MapperScan("com.pdsu.banmeng.mapper")
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

}

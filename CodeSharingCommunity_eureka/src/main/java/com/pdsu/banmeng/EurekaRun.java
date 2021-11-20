package com.pdsu.banmeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-20 19:48
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaRun {

    public static void main(String[] args) {
        SpringApplication.run(EurekaRun.class, args);
    }

}

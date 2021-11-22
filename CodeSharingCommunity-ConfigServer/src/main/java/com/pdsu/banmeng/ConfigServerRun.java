package com.pdsu.banmeng;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-20 19:46
 */
@SpringCloudApplication
@EnableConfigServer
public class ConfigServerRun {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerRun.class, args);
    }

}

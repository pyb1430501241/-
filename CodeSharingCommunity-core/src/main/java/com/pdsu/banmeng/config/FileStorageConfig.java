package com.pdsu.banmeng.config;

import com.pdsu.banmeng.service.IFileStorageService;
import com.pdsu.banmeng.service.impl.FileStorageService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-12-05 18:18
 */
@Configuration
public class FileStorageConfig {

    @Bean(initMethod = "init")
    public IFileStorageService fileStorageService() {
        return new FileStorageService();
    }

}

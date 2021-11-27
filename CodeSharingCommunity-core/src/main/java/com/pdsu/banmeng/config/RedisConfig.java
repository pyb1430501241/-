package com.pdsu.banmeng.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-22 17:41
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Bean@SuppressWarnings("all")
    public RedisCacheManager cacheManager(RedisTemplate<String, String> redisTemplate) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        config = config.disableCachingNullValues();
        config = config.entryTtl(Duration.ofSeconds(30L));

        RedisCacheManager cache = RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(redisTemplate.getConnectionFactory())
                .cacheDefaults(config)
                .transactionAware()
                .build();
        return cache;
    }

}

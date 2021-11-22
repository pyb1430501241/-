package com.pdsu.banmeng.context;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-22 17:34
 */
@Component
@Lazy(false)
public class RedisContext implements ApplicationContextAware {

    protected static RedisTemplate<String, String> redisTemplate;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        RedisContext.redisTemplate = applicationContext.getBean(StringRedisTemplate.class);
    }

}

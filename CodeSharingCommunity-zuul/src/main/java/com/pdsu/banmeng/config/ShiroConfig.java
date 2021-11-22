package com.pdsu.banmeng.config;

import com.pdsu.banmeng.shiro.WebCookieRememberMeManager;
import com.pdsu.banmeng.shiro.WebSessionManager;
import com.pdsu.banmeng.utils.DateUtils;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-20 20:16
 */
@Configuration
@SuppressWarnings("all")
public class ShiroConfig {

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Bean
    public RememberMeManager rememberMeManager() {
        return new WebCookieRememberMeManager();
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis, 使用的是shiro-redis开源插件
     *
     * @return RedisSessionDAO
     */
    @Bean
    public RedisSessionDAO redisSessionDAO(JavaUuidSessionIdGenerator generator) {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setSessionIdGenerator(generator);
        redisSessionDAO.setExpire((int) DateUtils.NEWS_TIME_WEEK);
        return redisSessionDAO;
    }

    /**
     * 配置shiro redisManager, 使用的是shiro-redis开源插件
     *
     * @return RedisManager
     */
    private RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("localhost:6379");
        redisManager.setPassword(redisPassword);
        redisManager.setTimeout((int) DateUtils.NEWS_TIME_WEEK);
        return redisManager;
    }


    /**
     * cacheManager 缓存 redis实现, 使用的是shiro-redis开源插件
     *
     * @return RedisCacheManager
     */
    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        // 必须要设置主键名称，shiro-redis 插件用过这个缓存用户信息
        redisCacheManager.setPrincipalIdFieldName("id");
        return redisCacheManager;
    }

    /**
     * Session ID 生成器
     *
     * @return JavaUuidSessionIdGenerator
     */
    @Bean
    public JavaUuidSessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    @Bean
    public SessionManager sessionManager(RedisSessionDAO sessionDAO) {
        WebSessionManager webSessionManager = new WebSessionManager();
        webSessionManager.setSessionDAO(sessionDAO);
        return webSessionManager;
    }

    @Bean
    public SessionsSecurityManager webSecurityManager(SessionManager sessionManager
            , RedisCacheManager cacheManager, Realm realm, RememberMeManager rememberMeManager) throws Exception {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager(realm);
        defaultWebSecurityManager.setSessionManager(sessionManager);
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager);
        defaultWebSecurityManager.setCacheManager(cacheManager);
        return defaultWebSecurityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(org.apache.shiro.mgt.SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        Map<String, String> hashMap = new HashMap<>();

        hashMap.put("/**", "anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(hashMap);
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        return shiroFilterFactoryBean;
    }

}

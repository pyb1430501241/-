package com.pdsu.banmeng.config;

import com.pdsu.banmeng.shiro.WebCookieRememberMeManager;
import com.pdsu.banmeng.shiro.WebSessionManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class ShiroConfig implements ApplicationContextAware {

    private ApplicationContext applicaiton;

    @Bean
    public RememberMeManager rememberMeManager() {
        return new WebCookieRememberMeManager();
    }

    @Bean
    public SessionsSecurityManager webSecurityManager() throws Exception {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager(this.applicaiton.getBean(Realm.class));
        defaultWebSecurityManager.setSessionManager(new WebSessionManager());
        defaultWebSecurityManager.setRememberMeManager(this.applicaiton.getBean(RememberMeManager.class));
        return defaultWebSecurityManager;
    }

    @Bean
    @Order
    public ShiroFilterFactoryBean shiroFilterFactoryBean(org.apache.shiro.mgt.SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        Map<String, String> hashMap = new HashMap<>();

        hashMap.put("/**", "anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(hashMap);
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        return shiroFilterFactoryBean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicaiton = applicationContext;
    }

}

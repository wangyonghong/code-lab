package me.yonghong.springboot.lab;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

    @Bean
    public Realm realm() {
        return null;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        return null;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        return null;
    }

}

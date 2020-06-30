package me.yonghong.springboot.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * 自动化配置类
 * {@link UserDetailsServiceAutoConfiguration}
 * <p>
 * 内存级别用户对象
 * {@link InMemoryUserDetailsManager}
 * {@link SecurityProperties.User}
 */
@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }
}

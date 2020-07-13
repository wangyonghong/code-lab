package me.yonghong.springboot.lab;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60)
public class SessionConfiguration {

//    /**
//     * 自定义 sessionid 在 Cookie 中，使用别的 KEY 呢，例如说 "JSESSIONID"
//     */
//    @Bean
//    public CookieHttpSessionIdResolver sessionIdResolver() {
//        // 创建 CookieHttpSessionIdResolver 对象
//        CookieHttpSessionIdResolver sessionIdResolver = new CookieHttpSessionIdResolver();
//
//        // 创建 DefaultCookieSerializer 对象
//        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
//        sessionIdResolver.setCookieSerializer(cookieSerializer); // 设置到 sessionIdResolver 中
//        cookieSerializer.setCookieName("JSESSIONID");
//
//        return sessionIdResolver;
//    }

//    /**
//     * session 存放在 Header 之中
//     */
//    @Bean
//    public HeaderHttpSessionIdResolver sessionIdResolver() {
//        return new HeaderHttpSessionIdResolver("token");
//    }
}

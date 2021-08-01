package me.yonghong.springboot.lab;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.FlushMode;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;

/**
 * flushMode = FlushMode.IMMEDIATE 在请求未返回 response 时也能存入 Redis
 * flushMode = FlushMode.ON_SAVE 在请求返回 response 后才存入 Redis
 * 由于默认是 FlushMode.ON_SAVE，所以使用时需要小心
 */
@Configuration
@EnableRedisHttpSession(flushMode = FlushMode.IMMEDIATE) // 自动化配置 Spring Session 使用 Redis 作为数据源
public class SessionConfiguration {

    /**
     * 创建 {@link RedisIndexedSessionRepository} 使用的 RedisSerializer Bean 。
     * <p>
     * 具体可以看看 {@link RedisHttpSessionConfiguration#setDefaultRedisSerializer(RedisSerializer)} 方法，
     * 它会引入名字为 "springSessionDefaultRedisSerializer" 的 Bean 。
     *
     * @return RedisSerializer Bean
     */
    @Bean(name = "springSessionDefaultRedisSerializer")
    public RedisSerializer springSessionDefaultRedisSerializer() {
        return RedisSerializer.json();
    }

}

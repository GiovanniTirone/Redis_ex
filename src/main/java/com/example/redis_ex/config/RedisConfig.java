package com.example.redis_ex.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

    @Value("${redis.host}")
    private String redishost;

    @Value(("${redis.port}"))
    private int redisPort;

    @Value(("${redis.password}"))
    private String redisPassword;

    @Value("${redis.database}")
    private String redisDatabase;

    @Bean
    JedisConnectionFactory jedisConnectionFactory ()
    {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redishost,redisPort);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(redisPassword));
        redisStandaloneConfiguration.setDatabase(0);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate ()
    {
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }



}

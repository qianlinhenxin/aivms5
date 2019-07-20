package com.qlhx.service.control.realize.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisUtil {
	
    
//	@Bean("myRedis")
//	RedisTemplate<String, Object> myRedisTemplate()
//	{
//		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
//		redisTemplate.setConnectionFactory(jedisConnectionFactory());
//		RedisSerializer stringSerializer = new StringRedisSerializer();
//		redisTemplate.setKeySerializer(stringSerializer);
//		redisTemplate.setValueSerializer(stringSerializer);
//		redisTemplate.afterPropertiesSet();
//		return redisTemplate;
//	}
//
//	@Bean
//	@ConfigurationProperties(prefix="spring.redis")
//	JedisConnectionFactory jedisConnectionFactory() {
//		JedisConnectionFactory factory = new JedisConnectionFactory();
//		factory.setPoolConfig(jedisPoolConfig());
//		return factory;
//	}
//
	@Bean
	@ConfigurationProperties(prefix="spring.redis.jedis.pool")
	public JedisPoolConfig jedisPoolConfig() {
		return new JedisPoolConfig();
	}

}

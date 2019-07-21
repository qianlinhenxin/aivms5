//package com.qlhx.service.user.realize.util;
//
//import com.qlhx.user.util.redis.JedisManager;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//
//@Configuration
//public class RedisUtil {
//
//    //日后换成redisTempale
//
//
//    @Value("${spring.redis.host}")
//    private String url ;
//
//    @Value("${spring.redis.port}")
//    private String port ;
//
//    @Value("${spring.redis.timeout}")
//    private String timeout;
//
//    @Value("${spring.redis.password}")
//    private String passWord;
//
//    @Bean("jedisPool")
//    JedisPool getjedisPool(){
//
//        /**
//         * spring:
//         *   redis:
//         *     host: 127.0.0.1
//         *     port: 6379
//         *     password: 123456
//         *     timeout: 5000
//         *     database: 0
//         *     pool:
//         *       max-active: 10
//         *       max-idle: 8
//         *       min-idle: 2
//         *       max-wait: 100
//         */
//        Integer portNew = Integer.valueOf(port);
//        Integer timeOut = Integer.valueOf(timeout);
//        JedisPool jedisPool = null;
//        jedisPool = new JedisPool(jedisPoolConfig(), url, portNew, timeOut, passWord);
//        return jedisPool;
//    }
//
//    @Bean("jedisManager")
//    JedisManager getJedisManager(){
//        return new JedisManager();
//    }
//
////	@Bean("myRedis")
////	RedisTemplate<String, Object> myRedisTemplate()
////	{
////		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
////		redisTemplate.setConnectionFactory(jedisConnectionFactory());
////		RedisSerializer stringSerializer = new StringRedisSerializer();
////		redisTemplate.setKeySerializer(stringSerializer);
////		redisTemplate.setValueSerializer(stringSerializer);
////		redisTemplate.afterPropertiesSet();
////		return redisTemplate;
////	}
////
////	@Bean
////	@ConfigurationProperties(prefix="spring.redis")
////	JedisConnectionFactory jedisConnectionFactory() {
////		JedisConnectionFactory factory = new JedisConnectionFactory();
////		factory.setPoolConfig(jedisPoolConfig());
////		return factory;
////	}
//
//	@Bean
//	@ConfigurationProperties(prefix="spring.redis.jedis.pool")
//	public JedisPoolConfig jedisPoolConfig() {
//		return new JedisPoolConfig();
//	}
//
//}

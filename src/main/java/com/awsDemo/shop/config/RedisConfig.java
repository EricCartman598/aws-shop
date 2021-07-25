package com.awsDemo.shop.config;

import com.awsDemo.shop.product.domain.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.aws.autoconfigure.cache.ElastiCacheAutoConfiguration;
import org.springframework.cloud.aws.cache.ElastiCacheFactoryBean;
import org.springframework.cloud.aws.cache.config.annotation.CacheClusterConfig;
import org.springframework.cloud.aws.cache.config.annotation.ElastiCacheCacheConfigurer;
import org.springframework.cloud.aws.cache.config.annotation.ElastiCacheCachingConfiguration;
import org.springframework.cloud.aws.cache.config.annotation.EnableElastiCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
//@EnableCaching
//@EnableElastiCache({@CacheClusterConfig(name = "awsshopelasticache")})
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private Integer port;

//    @Bean
//    JedisConnectionFactory jedisConnectionFactory() {
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);
//        return new JedisConnectionFactory(redisStandaloneConfiguration);
//    }
//
//    @Bean(value = "redisTemplate")
//    public RedisTemplate<String, Product> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
//        RedisTemplate<String, Product> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(jedisConnectionFactory);
//        return redisTemplate;
//    }
//
//    @Bean(name = "cacheManager")
//    public CacheManager cacheManager(JedisConnectionFactory jedisConnectionFactory) {
//        return RedisCacheManager.builder(jedisConnectionFactory)
//                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig())
//                .build();
//    }

//    @Bean
//    public JedisPoolConfig jedisPoolConfig() {
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setBlockWhenExhausted(true);
//        jedisPoolConfig.setTestOnBorrow(true);
//        jedisPoolConfig.setMaxTotal(10000);
//        jedisPoolConfig.setMaxIdle(10000);
//        jedisPoolConfig.setMinIdle(10000);
//        jedisPoolConfig.setMaxWaitMillis(100000);
//        return jedisPoolConfig;
//    }

//    public ElastiCacheCacheConfigurer elastiCacheAutoConfiguration() {
//        new ElastiCacheCacheConfigurer()
//    }
}

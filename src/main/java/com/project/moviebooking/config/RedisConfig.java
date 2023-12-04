package com.project.moviebooking.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

/**
 * Configuration class responsible for setting up and configuring Redis
 * for caching purposes within the movie booking application.
 */
@Configuration
@EnableCaching
public class RedisConfig {

    /**
     * Redis server host.
     */
    @Value("${spring.redis.host}")
    private String host;

    /**
     * Redis server port.
     */
    @Value("${spring.redis.port}")
    private String port;

    /**
     * Timeout for connecting to Redis server.
     */
    @Value("${redis.timeout}")
    private String timeout;

    /**
     * Time-to-live for cached data in Redis.
     */
    @Value("${spring.redis.ttl}")
    private int cacheTimeToLive;

    /**
     * Redis server username (if applicable).
     */
    @Value("${spring.redis.username}")
    private String username;

    /**
     * Redis server password (if applicable).
     */
    @Value("${spring.redis.password}")
    private String password;

    /**
     * Configures and creates a JedisConnectionFactory to establish a connection with the Redis server.
     *
     * @return JedisConnectionFactory instance for Redis connection.
     */
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(Integer.parseInt(port));
        redisStandaloneConfiguration.setUsername(username);
        redisStandaloneConfiguration.setPassword(password);

        JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
        jedisClientConfiguration.connectTimeout(Duration.ofSeconds(Integer.parseInt(timeout)));

        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration.build());
    }

    /**
     * Configures and creates a RedisTemplate for interacting with Redis server.
     *
     * @return RedisTemplate instance for Redis operations.
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

    /**
     * Configures and creates a RedisCacheManager to manage caching operations in Redis with specific time-to-live settings.
     *
     * @return RedisCacheManager instance for caching in Redis.
     */
    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheConfiguration ttlExpirationDefaults =
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMillis(cacheTimeToLive));

        return RedisCacheManager.builder(jedisConnectionFactory())
                .cacheDefaults(ttlExpirationDefaults)
                .build();
    }
}

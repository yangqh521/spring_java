package com.recover.yqh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
//@PropertySource("classpath:jdbc.properties")
public class RedisConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	public JedisPoolConfig jedisPoolConfig(){
	    return new JedisPoolConfig();
	}
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory(){
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setPoolConfig(jedisPoolConfig());
		jedisConnectionFactory.setUsePool(true);
		jedisConnectionFactory.setHostName(env.getProperty("redis.hostName"));
		jedisConnectionFactory.setPort(Integer.parseInt(env.getProperty("redis.port")));
		jedisConnectionFactory.setPassword(env.getProperty("redis.password"));
		jedisConnectionFactory.setDatabase(Integer.parseInt(env.getProperty("redis.database")));
		return jedisConnectionFactory;
	}
	
	@Bean
	public RedisTemplate<String,Object> redisTemplate(){
		RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		return redisTemplate;
	}
	
	

}

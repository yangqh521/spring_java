package com.recover.yqh.controller;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/redis")
public class RedisController {

	@Autowired
    private RedisTemplate<String, Object> redisTemplate;
	
	@RequestMapping("/test")
	public void test(){
		String timestamp = String.valueOf(new Date().getTime());
		System.out.println("timestamp >>> " + timestamp);
		redisTemplate.opsForValue().set("abc" , timestamp, 60 * 60, TimeUnit.SECONDS);
	}
	
	
}

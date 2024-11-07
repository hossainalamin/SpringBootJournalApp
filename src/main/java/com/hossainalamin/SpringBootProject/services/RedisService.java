package com.hossainalamin.SpringBootProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void testRedisConnection() {
        redisTemplate.opsForValue().set("testKey", "Hello, Redis!");
        String value = redisTemplate.opsForValue().get("testKey");
        System.out.println("Value from Redis: " + value);
    }

    public void getWeather(String key){
        redisTemplate.opsForValue().get(key);
    }
}

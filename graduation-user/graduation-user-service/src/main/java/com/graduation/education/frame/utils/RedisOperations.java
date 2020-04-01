package com.graduation.education.frame.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/1 13:50
 */
@Component
@Slf4j
public class RedisOperations {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * get key
     *
     * @param key
     * @return
     */
    public String get(String key) {
        try {
            ValueOperations<String, String> stringOps = redisTemplate.opsForValue();
            return stringOps.get(key);
        } catch (Exception e) {
            log.warn("redis服务出现异常", e);
            return null;
        }
    }

    /**
     * set key value
     * expire time
     *
     * @param key
     * @param value
     * @param time
     * @param timeUnit
     */
    public void set(String key, String value, long time, TimeUnit timeUnit) {
        try {
            ValueOperations<String, String> stringOps = redisTemplate.opsForValue();
            stringOps.set(key, value, time, timeUnit);
        } catch (Exception e) {
            log.warn("redis服务出现异常", e);
        }
    }

    /**
     * hget key field
     *
     * @param key
     * @param field
     * @return string
     */
    public String hget(String key, String field) {
        try {
            HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
            return hashOperations.get(key, field);
        } catch (Exception e) {
            log.warn("redis服务出现异常", e);
            return null;
        }
    }


    public String hset(String key, String field, String value) {
        try {
            HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
            hashOperations.put(key, field, value);
            return hashOperations.get(key, field);
        } catch (Exception e) {
            log.warn("redis服务出现异常", e);
            return null;
        }
    }
}

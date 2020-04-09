package com.graduation.education.frame.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

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
    private JedisCluster jedisCluster;

    /**
     * get key
     *
     * @param key
     * @return
     */
    public String get(String key) {
        try {
            return jedisCluster.get(key);
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
            jedisCluster.setex(key, (int) timeUnit.toSeconds(time), value);
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
            return jedisCluster.hget(key, field);
        } catch (Exception e) {
            log.warn("redis服务出现异常", e);
            return null;
        }
    }


    public void hset(String key, String field, String value) {
        try {
            jedisCluster.hset(key, field, value);
        } catch (Exception e) {
            log.warn("redis服务出现异常", e);
        }
    }

    public void hsetEx(String key,String field , String value , long time, TimeUnit timeUnit){
        try {
            jedisCluster.hset(key, field, value);
            Long ttl = jedisCluster.ttl(key);
            if(ttl == 0){
                //第一次设置过期时间
                jedisCluster.expire(key , (int) timeUnit.toSeconds(time));
            }
        } catch (Exception e) {
            log.warn("redis服务出现异常", e);
        }
    }
}

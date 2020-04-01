package com.takefly.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/3/28 19:17
 */
public class JedisSentinelTest {
    public static void main(String[] args) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(50);
        config.setMinIdle(10);
        Set<String> sentinels = new HashSet<>();
        sentinels.add(new HostAndPort("119.23.64.69" , 26379).toString());
        sentinels.add(new HostAndPort("39.100.6.131" , 26379).toString());
        sentinels.add(new HostAndPort("218.78.84.3" , 26379).toString());

        String masterName = "graduation";
        JedisSentinelPool sentinelPool = new JedisSentinelPool(masterName, sentinels, config, 5000 , "980518");
        Jedis jedis = sentinelPool.getResource();
        for (int i = 0; i < 1000; i++) {
            System.out.println(jedis.set("sentinel:test", "exists"));
            System.out.println(jedis.get("sentinel:test"));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        jedis.close();
        sentinelPool.close();
    }
}

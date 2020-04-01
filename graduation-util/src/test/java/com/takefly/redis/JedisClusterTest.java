package com.takefly.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/3/28 19:17
 */
public class JedisClusterTest {
    public static void main(String[] args) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(50);
        config.setMinIdle(10);
        Set<HostAndPort> sentinels = new HashSet<>();
        sentinels.add(new HostAndPort("119.23.64.69", 6380));
        sentinels.add(new HostAndPort("119.23.64.69", 6381));
        sentinels.add(new HostAndPort("39.100.6.131", 6379));
        sentinels.add(new HostAndPort("39.100.6.131", 6380));
        sentinels.add(new HostAndPort("218.78.84.3", 6379));
        sentinels.add(new HostAndPort("218.78.84.3", 6380));

        String masterName = "graduation";
        JedisCluster jedisCluster = new JedisCluster(sentinels, 3000, 3000,
                5, "980518", "clusterTest", config);
        /*for (int i = 0; i < 1000; i++) {
            int t;
            System.out.println(jedisCluster.set("sentinel:test" + (t = (int) (Math.random() * 100000000)), UUID.randomUUID().toString()));
            System.out.println(jedisCluster.get("sentinel:test" + t));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

        //执行lua脚本
        for (int i = 0; i < 100; i++) {
            System.out.println(jedisCluster.evalsha("6ac19b7a2352046f1d74cbc6ae21ba11bc289011",
                    1, "IP:127.0.0.1", "30", "10"));
        }
        jedisCluster.close();
    }
}

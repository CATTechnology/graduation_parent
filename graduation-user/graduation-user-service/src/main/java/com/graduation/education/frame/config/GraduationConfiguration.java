package com.graduation.education.frame.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/3 23:30
 */
@Component
public class GraduationConfiguration {

    @Value(value = "${spring.redis.password}")
    private String password;

    @Value(value = "${spring.redis.cluster.nodes}")
    private String nodes;

    @Value(value = "${spring.redis.timeout}")
    private Integer timeout;

    @Value(value = "${spring.redis.lettuce.pool.max-active}")
    private Integer maxActive;

    @Value(value = "${spring.redis.lettuce.pool.max-wait}")
    private Integer maxWait;

    @Value(value = "${spring.redis.lettuce.pool.max-idle}")
    private Integer maxIdle;

    @Value(value = "${spring.redis.lettuce.pool.min-idle}")
    private Integer minIdle;

    @Bean
    public JedisCluster createJedisCluster() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setTestOnReturn(true);
        String[] hostAndPortArr = nodes.split("\\,");
        Set<HostAndPort> hostAndPorts = new HashSet<>();
        for (String hostPort : hostAndPortArr) {
            String[] arr = hostPort.split("\\:");
            hostAndPorts.add(new HostAndPort(arr[0], Integer.parseInt(arr[1])));
        }
        JedisCluster cluster = new JedisCluster(hostAndPorts, timeout, timeout, 10, password, "graduation", poolConfig);
        return cluster;
    }
}

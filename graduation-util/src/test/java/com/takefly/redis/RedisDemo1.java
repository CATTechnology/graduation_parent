package com.takefly.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/3/24 21:03
 */
public class RedisDemo1 {

    @Test
    public void connection() {
        Jedis jedis = new Jedis("218.78.84.3", 6379);
        jedis.auth("980518");
        jedis.connect();


        //strings
        //set key value
        jedis.set("user:1:username", "dailingfei");
        //mset key1 value1 key2 value2 ....
        jedis.mset("user:1:age", "20", "user:1:sex", "男");
        //get key key1..
        System.out.println(jedis.get("user:1:age"));
        //mget key1 key2
        System.out.println(jedis.mget("user:1:username", "user:1:sex"));
        //append key value
        System.out.println(jedis.append("user:1:username", "-天使之王"));
        //del key 删除key
//        jedis.del("user:1:username");
        //完整的分布式锁的原子操作的命令 set key value [ex second | px 毫秒] [NX|XX]
        System.out.println(jedis.setex("graduation:user:1001", 10, "dailingfei"));
        //setnx key value 也是分布式锁的实现
        System.out.println(jedis.setnx("graduation:pay:1001", "pay_lock_by_user_id"));
        //设置过期时间
        jedis.expire("graduation:pay:1001", 10);
        //setbit pos value//位操作 非常节省内存
        jedis.set("a", "b");
        jedis.setbit("a", 7, "1");
        System.out.println("a:" + jedis.get("a"));
        //计数器
        for (int i = 0; i < 20; i++) {
            //jedis.incr("askCount");
            jedis.incrBy("askCount", 1000);
        }
        System.out.println("askCount" + jedis.get("askCount"));
        //strlen key
        System.out.println("username's length=" + jedis.strlen("user:1:username"));

        //通用型命令
        //type key
        System.out.println(jedis.type("user:1:username"));
        //Object encoding
        System.out.println(jedis.objectEncoding("user:1:username"));
        //expire 通用型key
        jedis.expire("graduation:pay:1001", 10);

        //hset key field value
        jedis.hset("distribute:lock", "product:1001:count", "2");

        //hmset
        HashMap<String, String> fileValue = new HashMap<>();
        fileValue.put("product:1001:single_price", "1000");
        fileValue.put("product:1001:total", "2000");
        fileValue.put("product:1001:count", "2");
        fileValue.put("product:1001:sort", "1");
        fileValue.put("product:1001:name", "tianshi_no.1");
        fileValue.put("product:1001:price", "1000.23");

        jedis.hmset("distribute:lock", fileValue);

        //hincrBy key field
        System.out.println(jedis.hincrBy("distribute:lock", "product:1001:sort", 25));
        //hincrByFloat
        System.out.println(jedis.hincrByFloat("distribute:lock", "product:1001:price", 25));
        //hsetnx key field value
        jedis.hsetnx("hdistribute", "lock", "true");
        //但是expire针对key设置过期时间，不能针对field
        jedis.hexists("distribute:lock", "product:1001:count");
        //hvals key // 返回所有的value
        System.out.println(jedis.hvals("distribute:lock"));
        //hgetall key 返回field value的键值映射集合
        System.out.println(jedis.hgetAll("distribute:lock"));
        System.out.println(jedis.hdel("distribute:lock", "product:1001:sort"));
        //hscan key match pattern Count count
        //hlen key
        System.out.println(jedis.hlen("distribute:lock"));
        jedis.hstrlen("distribute:lock", "product:1001:name");

        //list :适合做消息队列 |  模拟栈  | stack | 博客系统订阅大V
        //lpush key v1 v2 ...
        System.out.println(jedis.lpush("msg:10001:lick", "30001", "30002", "30003", "30004"));
        //lpop key
        String str = null;
        while((str = jedis.lpop("msg:10001:lick")) != null){
            System.out.print("->"+str);
        }
        jedis.lpush("msg:10001:lick", "30001", "30002", "30003", "30004");
        //llen key
        System.out.println(jedis.llen("msg:10001:lick"));
        System.out.println(jedis.lrange("msg:10001:lick", -1, 0));
        jedis.rpush("msg:10001:lick", "30001", "30002", "30003", "30004");
        //blpop key key ... // 从给定的list中取出一个元素
        jedis.blpop("msg:10001:lick" , "10");

        //set集合
        //sadd
        System.out.println(jedis.sadd("weibo:activity:1001", "111", "aaa", "sss", "ccc", "ddd", "zzz"));
        //smemebers
        System.out.println(jedis.smembers("weibo:activity:1001"));
        //sismeber
        System.out.println(jedis.sismember("weibo:activity:1001", "aaa"));
        //scard key
        System.out.println(jedis.scard("weibo:activity:1001"));
        //srem key member
        System.out.println(jedis.srem("weibo:activity:1001", "aaa"));
        //抽奖活动srandmember key count  随机的从集合中获取count个不重复的成员，不从原来的集合中删除
        System.out.println(jedis.srandmember("weibo:activity:1001", 2));
        //spop key count 随机的从集合中拿出count个不重复的成员，并且从原来的集合中删除这count个成员
        System.out.println(jedis.spop("weibo:activity:1001", 1));
        //sunion key1 key2 ....
        jedis.sadd("weibo:activity:1002" , "111", "aaa", "sss", "ccc" , "yyyy" , "ttt" , "mmmm");
        jedis.sadd("weibo:activity:1003" , "111", "aaa", "sss", "ccc" , "yyyy" , "ttt" , "mmmm" , "ssssss");
        System.out.println(jedis.sunion("weibo:activity:1001", "weibo:activity:1002"));
        //sdiff
        System.out.println(jedis.sdiff("weibo:activity:1001", "weibo:activity:1002"));
        //sinter key key2 ...
        System.out.println(jedis.sinter("weibo:activity:1001", "weibo:activity:1002"));
        jedis.sinterstore("weibo:activity:1001", "weibo:activity:1002");



        //zadd key scope members ...
        jedis.zadd("weibo:hot:1001" , 1000000000 , "hot1");
        HashMap<String, Double> member = new HashMap<>();
        for (int i = 1; i <= 100000; i++) {
            member.put("news:hot:"+ i, Math.random() * 1000000000000000000L);
        }
        jedis.zadd("weibo:news" , member);
        //zcard key 返回元素个数
        System.out.println(jedis.zcard("weibo:news"));
        //zrange key start stop 获取从start到stop位置的元素（有序）
        System.out.println(jedis.zrange("weibo:news", 0, 9));
        //zrevrange key 0 9 // 获取最大的10个元素
        System.out.println(jedis.zrevrange("weibo:news", 0, 9));
        //zrangebyscore key start stop 获取指定区域的元素
        System.out.println(jedis.zrangeByScore("weibo:news", 52011314, 10000000000L, 0, 10));

        //
    }


}

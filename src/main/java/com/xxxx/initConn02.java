package com.xxxx;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 *
 *
 * 通过redis连接池获取连接对象
 */

public class initConn02 {
    public static void main(String[] args) {
        //初始化redis客户端连接池
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "192.168.253.100", 6379, 10000, "root");
        //从连接池获取连接
        Jedis jedis = jedisPool.getResource();

        //指定数据库 默认是0
        jedis.select(2);

        //使用ping命令，测试连接是否成功
        String result = jedis.ping();
        System.out.println(result);

        //添加一条数据
        jedis.set("username","xwp");

        //获取一条数据
        String username = jedis.get("username");
        System.out.println(username);


    }

}

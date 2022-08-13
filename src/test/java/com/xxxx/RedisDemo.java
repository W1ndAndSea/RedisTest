package com.xxxx;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;

@SpringBootTest
public class RedisDemo {
    @Autowired
    private JedisPool jedisPool;
    private Jedis jedis;




    @Test
    public void test1(){
        Jedis jedis = jedisPool.getResource();
        System.out.println(jedis.ping());
        jedis.close();
    }


    @Test
    public void test2(){
        jedis=jedisPool.getResource();
        /**
         *
         * 添加
         *
         * 获取
         *
         * 自增 自减
         *
         * 位操作
         *
         * 过期
         *
         * 删除
         */
        System.out.println(jedis.mset("redis1", "xixi", "redis2", "haha"));
        jedis.mget("redis1","redis2").forEach(s->{
            System.out.println(s);

        });

        jedis.setbit("xwp",10,true);
        jedis.setbit("xwp",11,true);
        jedis.setbit("xwp",12,true);
        jedis.setbit("xwp",13,true);
        jedis.setbit("xwp",14,true);
        jedis.setbit("xwp",15,true);
        System.out.println(jedis.bitcount("xwp"));
        jedis.expire("xwp",300);

        System.out.println(jedis.del("redis2"));
        System.out.println(jedis.get("redis2"));
        jedis.close();

    }


    @Test
    public void HashTest(){
        /**
         * 添加
         * 获取
         * 自增
         * 遍历
         */
        jedis=jedisPool.getResource();
        HashMap<String,String> map = new HashMap<>();
        map.put("id","1");
        map.put("name","xwp");
        map.put("account","10086");
        System.out.println(jedis.hmset("account::id::1", map));
        jedis.hmget("account::id::1","id","name","account").forEach(v->{
            System.out.println(v);


        });

    }
}

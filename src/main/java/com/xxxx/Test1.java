package com.xxxx;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Test1 {
    @Autowired
    private JedisPool jedisPool;


    private Jedis jedis=null;

    //初始化jedis对象
    @Before
    public void initConn(){
    jedis=jedisPool.getResource();
    }
    //释放资源
    @After
    public void testCloseConn(){
        if (jedis!=null){
            jedis.close();
        }
    }

    //操作String
    @Test
    public void testString(){
    //添加一条数据
        jedis.set("username","xwp");
        jedis.set("age","22");

        //添加多条数据
        jedis.mset("address","bj","sex","1");

        //获取一条数据
        String username = jedis.get("username");
        System.out.println(username);

    }

}

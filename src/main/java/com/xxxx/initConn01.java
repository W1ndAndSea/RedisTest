package com.xxxx;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 连接redis
 */

public class initConn01 {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.253.100", 6379);
        //设置认证密码
        jedis.auth("root");

        //指定数据库，默认是0
        jedis.select(1);

        //使用ping命令测试连接是否成功
        String result = jedis.ping();
        System.out.println(result);
        System.out.println("随便修改一下");

        //添加一条数据
        jedis.set("username", "xwp");

        //获取一条数据
        String username = jedis.get("username");
        System.out.println(username);


        // 添加一条数据
        jedis.set("username", "zhangsan");
        jedis.set("age", "18");
        // 添加多条数据 参数奇数为key 参数偶数为value
        jedis.mset("address", "bj", "sex", "1");
        // 获取一条数据
        String uname = jedis.get("username");
        System.out.println(username);
        // 获取多条数据
        List<String> list = jedis.mget("username", "age", "address", "sex");
        for (String str : list) {
            System.out.println(str);
        }
        // 删除
        //jedis.del("username");

        System.out.println("---------------------------");
        /**
         *
         * 操作hash
         */
        //添加一条数据
        jedis.hset("userInfo", "name", "xwp");

        //添加多条数据
        HashMap<String, String> map = new HashMap<>();
        map.put("age", "22");
        map.put("sex", "1");

        jedis.hmset("userInfo", map);

        //获取一条数据
        String hget = jedis.hget("userInfo", "name");
        System.out.println(hget);

        //获取多条数据

        List<String> mess = jedis.hmget("userInfo", "name", "age", "sex");
        for (String mes :
                mess) {
            System.out.println(mes);
        }

        //获取Hash类型所有的数据
        Map<String, String> userInfo = jedis.hgetAll("userInfo");
        for (Map.Entry<String, String> Info : userInfo.entrySet()) {
            System.out.println(Info.getKey() + "----->" + Info.getValue());

        }
        // 删除 用于删除hash类型数据
        //jedis.hdel("userInfo", "name");


        //释放资源
        if (jedis != null) {
            jedis.close();

        }
    }

}

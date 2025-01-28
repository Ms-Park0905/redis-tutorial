package org.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;


public class RedisList {
  public static void main(String[] args) {

    try (JedisPool jedisPool = new JedisPool("127.0.0.1", 6379)) {
      try (Jedis jedis = jedisPool.getResource()) {
        // 1. stack
//        jedis.rpush("stack1", "aaa");
//        jedis.rpush("stack1", "bbb");
//
//        List<String> stack1 = jedis.lrange("stack1", 0, -1);
//        stack1.forEach(System.out::println);
//
//        System.out.println(jedis.rpop("stack1"));
//        System.out.println(jedis.rpop("stack1"));

        // 2. queue
//        jedis.rpush("queue1", "aaa");
//        jedis.rpush("queue1", "bbb");
//        jedis.rpush("queue1", "ccc");

        System.out.println(jedis.lpop("queue1"));
        System.out.println(jedis.lpop("queue1"));

        List<String> stack1 = jedis.lrange("queue1", 0, -1);
        stack1.forEach(System.out::println);

        // 3. block
        List<String> blpop = jedis.blpop(10, "queue:blocking");
        if(blpop != null) {
          blpop.forEach(System.out::println);
        }
      }
    }
  }
}

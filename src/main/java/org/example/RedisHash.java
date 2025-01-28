package org.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;


public class RedisHash {
  public static void main(String[] args) {

    try (JedisPool jedisPool = new JedisPool("127.0.0.1", 6379)) {
      try (Jedis jedis = jedisPool.getResource()) {
        //hash

        Map<String, String> userInfos = new HashMap<>();
//        userInfos.put("name", "tester");
//        userInfos.put("email", "eamil@naver.com");
//        userInfos.put("tel", "010-xxxx-xxxx");
//
//        jedis.hset("users:2:info", userInfos);
//
//        System.out.println(jedis.hgetAll("users:2:info"));

        jedis.hdel("users:2:info", "tel");

        System.out.println(jedis.hgetAll("users:2:info"));

        jedis.hincrBy("users:2:info", "visits", 1);

      }
    }
  }
}

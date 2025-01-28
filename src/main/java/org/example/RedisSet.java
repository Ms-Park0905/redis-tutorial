package org.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;


public class RedisSet {
  public static void main(String[] args) {

    try (JedisPool jedisPool = new JedisPool("127.0.0.1", 6379)) {
      try (Jedis jedis = jedisPool.getResource()) {
        jedis.sadd("users:500:follow", "100", "200", "300");
        jedis.srem("users:500:follow", "100");

        jedis.smembers("users:500:follow").forEach(System.out::println);

        System.out.println(jedis.sismember("users:500:follow", "200"));
        System.out.println( jedis.scard("users:500:follow"));


        // 두 개의 key에서 공통된게 있는 것만 출력
        Set<String> sinter = jedis.sinter("users:500:follow", "users:400:follow");
        sinter.forEach(System.out::println);
      }
    }
  }
}

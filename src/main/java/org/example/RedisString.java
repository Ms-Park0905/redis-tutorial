package org.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.List;

public class RedisString {
  public static void main(String[] args) {
    System.out.println("Hello world!");

    try (JedisPool jedisPool = new JedisPool("127.0.0.1", 6379)) {
      try (Jedis jedis = jedisPool.getResource()) {
//        jedis.set("users:100:email", "pms0905@naver.com");
//        jedis.set("users:100:name", "minseok");
//        System.out.println(jedis.get("users:100:email"));
//        System.out.println(jedis.mget("users:100:email", "users:100:name"));
//
//        long counter = jedis.incr("counter");
//        System.out.println(counter);
//
//        counter = jedis.incrBy("counter", 10L);
//        System.out.println(counter);
//
//        counter = jedis.decrBy("counter", 10L);
//        System.out.println(counter);

        // 순차 실행 필요 없는 경우, 결과값 필요하지 않은 경우, redis 요청을 한 번 에 처리할 경우
        // Batch 크기를 나눠서 bulk 작업 때 유용할듯
        Pipeline pipeline = jedis.pipelined();
        pipeline.set("users:200:name", "test2");
        pipeline.set("users:200:email", "test2@naver.com");

        List<Object> pipelineDatas = pipeline.syncAndReturnAll();

        pipelineDatas.forEach(it -> System.out.println(it.toString()));
      }
    }
  }
}
package com.briup.queuesystem.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.nio.charset.StandardCharsets;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {

  @Bean
  public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
    StringRedisTemplate template = new StringRedisTemplate(factory);

    // 定义key序列化方式
    RedisSerializer<String> redisSerializer = new StringRedisSerializer();// Long类型会出现异常信息;需要我们上面的自定义key生成策略，一般没必要
    template.setKeySerializer(redisSerializer);
    RedisSerializer<Object> redisSerializerValue = new RedisSerializer<Object>() {

      @Override
      public byte[] serialize(Object t) throws SerializationException {
        try {
          if (null == t) {
            return null;
          }
          byte[] b = t instanceof String ? ((String) t).getBytes(StandardCharsets.UTF_8)
              : JSONObject.toJSON(t).toString().getBytes(StandardCharsets.UTF_8);
          return b;
        } catch (Exception e) {
          throw new SerializationException("serialize exception", e);
        }
      }

      @Override
      public String deserialize(byte[] bytes) throws SerializationException {
        try {
          if (null == bytes || bytes.length <= 0) {
            return null;
          }
          return new String(bytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
          throw new SerializationException("serialize exception", e);
        }
      }
    };
    template.setValueSerializer(redisSerializerValue);
    template.afterPropertiesSet();
    return template;
  }
}

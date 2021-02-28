package com.loving.config.indb;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.security.oauth2.provider.token.store.redis.StandardStringSerializationStrategy;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * json序列化器
 * @Author: yijunjun
 * @Date: 2021/2/28 16:10
 */
@Component
public class JsonSerializationStrategy extends StandardStringSerializationStrategy {
    @Override
    protected <T> T deserializeInternal(byte[] bytes, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(bytes,clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected byte[] serializeInternal(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsBytes(object);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

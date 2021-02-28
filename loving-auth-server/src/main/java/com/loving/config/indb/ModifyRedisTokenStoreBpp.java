package com.loving.config.indb;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.stereotype.Component;

/**
 * 实现后置处理器
 * 将redis序列化
 * @Author: yijunjun
 * @Date: 2021/2/28 16:28
 */
@Component
public class ModifyRedisTokenStoreBpp implements BeanPostProcessor {
    @Autowired
    private JsonSerializationStrategy strategy;

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof TokenStore) {
            RedisTokenStore redisTokenStore = (RedisTokenStore) bean;
            redisTokenStore.setSerializationStrategy(strategy);
            return redisTokenStore;
        }
        return bean;
    }
}

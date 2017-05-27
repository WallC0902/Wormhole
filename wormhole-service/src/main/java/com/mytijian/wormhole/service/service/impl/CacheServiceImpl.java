package com.mytijian.wormhole.service.service.impl;

import com.mytijian.wormhole.service.constant.CacheEnum;
import com.mytijian.wormhole.service.service.CacheService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangchangpeng on 2017/5/27.
 */
@Service("cacheService")
public class CacheServiceImpl implements CacheService<T> {

    @Autowired
    private RedisTemplate redisTemplate;

    private static Long CACHE_TIME = 60 * 30l ;

    private ValueOperations<String, T> operations;

    private ValueOperations<String, T> getValueOperations() {
        if (operations == null) {
            operations = redisTemplate.opsForValue();
        }
        return operations;
    }

    @Override
    public void set(String key, T t) {
        set(key, t, CACHE_TIME, TimeUnit.SECONDS);
    }

    @Override
    public void set(String key, T t, long time) {
        set(key, t, time, TimeUnit.SECONDS);
    }

    @Override
    public T get(String key) {
        operations = getValueOperations();
        return operations.get(key);
    }

    @Override
    public void del(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void set(String key, T t, long time, TimeUnit timeUnit) {
        operations = getValueOperations();
        operations.set(key, t, time, timeUnit);
    }

    @Override
    public String getKey(CacheEnum cacheEnum, String key) {
        return cacheEnum.getKey() + key;
    }
}

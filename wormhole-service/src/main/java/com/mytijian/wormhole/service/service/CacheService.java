package com.mytijian.wormhole.service.service;

import com.mytijian.wormhole.service.constant.CacheEnum;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangchangpeng on 2017/5/27.
 */
public interface CacheService<T> {

    void set(String key, T t);

    void set(String key, T t, long time);

    T get(String key);

    void del(String key);

    void set(String key, T t, long time, TimeUnit timeUnit);

    String getKey(CacheEnum cacheEnum, String key);
}

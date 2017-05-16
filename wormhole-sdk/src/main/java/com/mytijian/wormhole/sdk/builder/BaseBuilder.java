package com.mytijian.wormhole.sdk.builder;

import com.alibaba.fastjson.JSONObject;
import com.mytijian.wormhole.base.constant.Constants;

/**
 * Created by wangchangpeng on 2017/5/15.
 */
public class BaseBuilder {

    private String mid;

    private String secret;

    private String key;

    public JSONObject build(String mid)
    {
        JSONObject json = new JSONObject(true);
        json.put(Constants.M_ID, mid);
        return json;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

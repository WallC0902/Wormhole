package com.mytijian.wormhole.sdk.builder;

import com.alibaba.fastjson.JSONObject;
import com.mytijian.wormhole.base.constant.Constants;

/**
 * Created by wangchangpeng on 2017/5/15.
 */
public class BaseBuilder {

    private Integer mid;

    private String signature;

    private Long timestamp;

    public JSONObject build(Integer mid)
    {
        JSONObject json = new JSONObject(true);
        json.put(Constants.M_ID, mid);
        json.put(Constants.TIME_STAMP, System.currentTimeMillis());

        return json;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}

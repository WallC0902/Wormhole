package com.mytijian.wormhole.sdk.builder;

import com.alibaba.fastjson.JSONObject;
import com.mytijian.wormhole.base.constant.Constants;

/**
 * Created by wangchangpeng on 2017/5/15.
 */
public class BaseBuilder {

    private String mid;

    private String sign;

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


    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}

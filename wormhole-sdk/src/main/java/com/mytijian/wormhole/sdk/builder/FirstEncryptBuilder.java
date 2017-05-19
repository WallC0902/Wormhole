package com.mytijian.wormhole.sdk.builder;

import com.alibaba.fastjson.JSONObject;
import com.mytijian.wormhole.base.constant.Constants;
import com.mytijian.wormhole.base.tools.RSA;

/**
 * Created by wangchangpeng on 2017/5/15.
 */
public class FirstEncryptBuilder extends BaseBuilder{

    private String name;

    private String idCard;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

}

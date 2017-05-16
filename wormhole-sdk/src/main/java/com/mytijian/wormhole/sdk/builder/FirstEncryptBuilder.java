package com.mytijian.wormhole.sdk.builder;

import com.alibaba.fastjson.JSONObject;
import com.mytijian.wormhole.base.constant.Constants;
import com.mytijian.wormhole.sdk.util.EncryptUtil;

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

    public JSONObject build(String mid, String token) throws Exception{
        JSONObject json = super.build(mid);
        json.put(Constants.SECRET, EncryptUtil.encrypt(
                token,
                this.name,
                this.idCard
        ));

        json.put(Constants.KEY, EncryptUtil.getSalt());
        json.put(Constants.NAME, this.name);
        json.put(Constants.ID_CARD, this.idCard);

        return json;
    }
}

package com.mytijian.wormhole.sdk.sso.budiler;

import com.alibaba.fastjson.JSONObject;
import com.mytijian.wormhole.base.constant.ResRedirectEnum;
import com.mytijian.wormhole.sdk.builder.FirstEncryptBuilder;
import com.mytijian.wormhole.base.constant.Constants;

/**
 * Created by wangchangpeng on 2017/5/15.
 */
public class SSOBuilder extends FirstEncryptBuilder {

    private ResRedirectEnum resRedirect;

    private String mobile;

    private String gender;

    private String marriageStatus;

    public ResRedirectEnum getResRedirect() {
        return resRedirect;
    }

    public void setResRedirect(ResRedirectEnum resRedirect) {
        this.resRedirect = resRedirect;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMarriageStatus() {
        return marriageStatus;
    }

    public void setMarriageStatus(String marriageStatus) {
        this.marriageStatus = marriageStatus;
    }

    public JSONObject build(String mid, String token) throws Exception{
        JSONObject json = super.build(mid, token);

        json.put(Constants.RES_REDIRECRT, this.resRedirect);
        json.put(Constants.MOBILE, this.mobile);
        json.put(Constants.GENDER, this.gender);
        json.put(Constants.MARRIAGE_STATUS, this.marriageStatus);

        return json;
    }
}

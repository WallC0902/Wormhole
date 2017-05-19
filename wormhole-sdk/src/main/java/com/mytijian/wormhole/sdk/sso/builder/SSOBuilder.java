package com.mytijian.wormhole.sdk.sso.builder;

import com.alibaba.fastjson.JSONObject;
import com.mytijian.wormhole.base.constant.ResRedirectEnum;
import com.mytijian.wormhole.sdk.builder.FirstEncryptBuilder;
import com.mytijian.wormhole.base.constant.Constants;
import com.mytijian.wormhole.sdk.util.EncryptUtil;
import org.apache.commons.lang.StringUtils;

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

    public JSONObject build(String mid, String privateKey) throws Exception{
        JSONObject json = super.build(mid);
        if (StringUtils.isBlank(getName())) {
            throw new Exception("name is null");
        }
        json.put(Constants.NAME, getName());
        if (StringUtils.isBlank(getName())) {
            throw new Exception("idCard is null");
        }
        json.put(Constants.ID_CARD, getIdCard());

        if (StringUtils.isBlank(this.mobile)) {
            throw new Exception("mobile is null");
        }
        json.put(Constants.MOBILE, this.mobile);

        if (!StringUtils.isBlank(this.gender)) {
            json.put(Constants.GENDER, this.gender);
        }
        if (!StringUtils.isBlank(this.marriageStatus)) {
            json.put(Constants.MARRIAGE_STATUS, this.marriageStatus);
        }
        if (this.resRedirect == null) {
            this.resRedirect = ResRedirectEnum.INDEX;
        }
        json.put(Constants.RES_REDIRECRT, this.resRedirect);

        String data = json.toJSONString();
        json.put(Constants.SIGNATURE, EncryptUtil.sign(data, privateKey));

        return json;
    }
}

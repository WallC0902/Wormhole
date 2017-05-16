package com.mytijian.wormhole.web.domain;

import com.mytijian.wormhole.base.constant.ResRedirectEnum;

/**
 * Created by wangchangpeng on 2017/5/15.
 */
public class DecryptBO {

    private String mid;

    private String name;

    private String idCard;

    private ResRedirectEnum resRedirect;

    private String mobile;

    private String gender;

    private String marriageStatus;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

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
}

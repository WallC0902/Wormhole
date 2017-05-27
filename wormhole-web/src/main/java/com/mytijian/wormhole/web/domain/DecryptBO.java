package com.mytijian.wormhole.web.domain;

import com.mytijian.wormhole.base.constant.ResRedirectEnum;

import java.io.Serializable;

/**
 * Created by wangchangpeng on 2017/5/15.
 */
public class DecryptBO implements Serializable{

    private Integer mid;

    private String name;

    private String idCard;

    private ResRedirectEnum resRedirect;

    private String mobile;

    private String gender;

    private String marriageStatus;

    private String signature;

    private Long timeStamp;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}

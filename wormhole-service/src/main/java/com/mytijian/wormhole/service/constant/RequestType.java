package com.mytijian.wormhole.service.constant;

/**
 * 请求类型
 *
 * Created by wangchangpeng on 2017/5/11.
 */
public enum RequestType {

    TYPE_SSO("单点登录"),
    TYPE_USER_AUTH_SSO("用户授权"),
    TYPE_USER_AUTH_MEDICAL_REPORT("用户授权"),
    TYPE_QUERY_ORDER("订单查询"),
    TYPE_QUERY_MEDICAL_REPORT("体检报告查询");

    private String description;

    RequestType (String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

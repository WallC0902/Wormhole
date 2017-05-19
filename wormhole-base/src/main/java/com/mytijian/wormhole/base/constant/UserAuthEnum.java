package com.mytijian.wormhole.base.constant;

/**
 * Created by wangchangpeng on 2017/5/16.
 */
public enum UserAuthEnum {

    SSO_LOGIN("LOGIN", "http://...."),
    SSO_USER_AUTH_URL("REDIRECT", ""), // SSO授权页面跳转
    SSO_MEDICAL_REPORT_URL("REDIRECT", ""), // 体检报告授权页面跳转

        ;
    private String status;

    private String url;

    UserAuthEnum(String status, String url) {
        this.status = status;
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

package com.mytijian.wormhole.service.constant;

/**
 * Created by wangchangpeng on 2017/5/27.
 */
public enum CacheEnum {

    SESSION_KEY("wormhole:session:key:"),

    AUTH_SSO_KEY("wormhole:auth:sso:key:"),

    AUTH_MEDICAL_REPORT_KEY("wormhole:auth:medical:key:"),

    ;

    CacheEnum(String key) {
        this.key = key;
    }

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

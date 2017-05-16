package com.mytijian.wormhole.base.constant;

/**
 * 请求路径枚举
 *
 * Created by wangchangpeng on 2017/5/12.
 */
public enum ResRedirectEnum {

    INDEX("/"),
    HOSPITAL(""),
    PACKAGE(""),
    ORDER(""),
    ;

    private String url;

    ResRedirectEnum(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}

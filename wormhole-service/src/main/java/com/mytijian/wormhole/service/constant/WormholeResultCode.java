package com.mytijian.wormhole.service.constant;

/**
 * 应用系统级别的错误码
 *
 * Created by wangchangpeng on 2017/05/09.
 */
public enum WormholeResultCode {
    /**
     * 通用异常
     */
    SUCCESS("200", "success"),
    UNKNOWN("-1", "未知异常"),
    PARAM_ERROR("-101", "参数异常"),
    LOGIN_ERROR("-102", "登录异常"),
    DB_ERROR("-103", "数据层异常");

    private String code;

    private String message;

    WormholeResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }
}

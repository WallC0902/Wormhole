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
    LOGIN_ERROR("-101", "登录异常"),
    PARAM_ERROR("-102", "参数异常"),
    DB_ERROR("-103", "数据层异常"),

    SSO_AUTH_FAIL_EMPTY_MID("-104", "认证信息：mid为空"),
    SSO_AUTH_FAIL_EMPTY_SECRET("-105", "认证信息：secret为空"),
    SSO_AUTH_FAIL_EMPTY_KEY("-106", "认证信息：key为空"),
    SSO_AUTH_FAIL_EMPTY_MOBILE("-107", "认证信息：mobile为空"),

    SSO_AUTH_FAIL_DECRYPT("-108", "认证信息信息解密失败，请重试"),
    SSO_AUTH_FAIL_UMAMTCH("-109", "认证信息信息不匹配，请重试"),
    SSO_AUTH_FAIL_INVALID("-1010", "认证信息链接已失效，请重试"),

    SSO_AUTH_FAIL_TIMEOUT("-1011", "认证超时，请重试"),
    SSO_AUTH_FAIL_EMPTY_MSG("-1012", "授权失败，认证信息为空，请重试"),


    ;

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

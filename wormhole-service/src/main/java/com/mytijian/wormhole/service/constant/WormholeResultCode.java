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
    SSO_AUTH_FAIL_EMPTY_SIGNATURE("-105", "认证信息：signature为空"),
    SSO_AUTH_FAIL_EMPTY_NAME("-106", "认证信息：name为空"),
    SSO_AUTH_FAIL_EMPTY_ID_CARD("-107", "认证信息：id_card为空"),
    SSO_AUTH_FAIL_EMPTY_MOBILE("-108", "认证信息：mobile为空"),
    SSO_AUTH_FAIL_NO_MEAN("-109", "无效信息"),

    SSO_AUTH_FAIL_UMAMTCH("-1010", "认证信息信息不匹配，请重试"),
    SSO_AUTH_FAIL_INVALID_MSG("-1011", "认证信息链接已失效，请重试"),

    SSO_AUTH_FAIL_TIMEOUT("-1012", "认证超时，请重试"),
    SSO_AUTH_FAIL_INVALID("-1013", "平台账号已失效"),
    SSO_AUTH_FAIL_DECRYPT("-1014", "认证信息解密密失败，请重试"),
    SSO_AUTH_FAIL_MID_NOT_EXIT("-1015", "未注册平台"),

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

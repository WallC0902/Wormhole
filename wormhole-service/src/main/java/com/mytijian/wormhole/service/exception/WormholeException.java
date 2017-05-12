package com.mytijian.wormhole.service.exception;

/**
 * 统一错误码异常
 *
 * Created by wangchangpeng on 2017/05/09.
 */
public class WormholeException extends RuntimeException {

    /**
     * 错误代码
     */
    private String code;

    /**
     * 错误消息
     */
    private String message;

    public WormholeException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

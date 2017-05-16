package com.mytijian.wormhole.service.exception;

import com.mytijian.wormhole.service.constant.WormholeResultCode;

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

    public WormholeException(WormholeResultCode wormholeResultCode) {
        super(wormholeResultCode.getMessage());
        this.code = wormholeResultCode.getCode();
        this.message = wormholeResultCode.getMessage();
    }

    public WormholeException(String message) {
        super(message);
        this.code = WormholeResultCode.UNKNOWN.getCode();
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

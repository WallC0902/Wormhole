package com.mytijian.wormhole.sdk.exception;

/**
 * Created by wangchangpeng on 2017/5/22.
 */
public class WormholeException extends RuntimeException {

    private String message;

    public WormholeException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

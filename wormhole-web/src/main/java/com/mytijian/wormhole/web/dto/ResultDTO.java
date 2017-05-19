package com.mytijian.wormhole.web.dto;

import com.mytijian.wormhole.service.constant.WormholeResultCode;

/**
 * 返回体
 *
 * Created by wangchangpeng on 2017/05/09.
 */
public class ResultDTO<T> {
    /**
     * 响应代码
     */
    private String code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 数字签名
     */
    private String signature;

    /**
     * 响应结果
     */
    private T result;

    public ResultDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultDTO(T result) {
        this.code = WormholeResultCode.SUCCESS.getCode();
        this.message = WormholeResultCode.SUCCESS.getMessage();
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}

package com.mytijian.wormhole.base.dto;

import com.mytijian.wormhole.base.constant.UserAuthEnum;

/**
 * Created by wangchangpeng on 2017/5/16.
 */
public class UserAuthDO {

    private String status;

    private String url;

    public UserAuthDO(UserAuthEnum userAuthEnum) {
        this.status = userAuthEnum.getStatus();
        this.url = userAuthEnum.getUrl();
    }

    public UserAuthDO(String status, String url) {
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

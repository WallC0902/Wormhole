package com.mytijian.wormhole.service.service;

import com.baomidou.mybatisplus.service.IService;
import com.mytijian.wormhole.dao.model.UserAuthorization;
import com.mytijian.wormhole.service.constant.RequestType;

/**
 * Created by wangchangpeng on 2017/5/9.
 */
public interface UserAuthorizationService extends IService<UserAuthorization> {

    boolean checkUserAuthoriz (String mid, String idCard, RequestType requestType);
}
package com.mytijian.wormhole.service.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mytijian.wormhole.service.service.UserAuthorizationService;
import com.mytijian.wormhole.dao.dao.UserAuthorizationMapper;
import com.mytijian.wormhole.dao.model.UserAuthorization;
import org.springframework.stereotype.Service;

/**
 * Created by wangchangpeng on 2017/5/9.
 */
@Service("userAuthorizationService")
public class UserAuthorizationServiceImpl extends ServiceImpl<UserAuthorizationMapper, UserAuthorization> implements UserAuthorizationService {
}

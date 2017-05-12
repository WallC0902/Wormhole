package com.mytijian.wormhole.service.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mytijian.wormhole.service.service.UserOperLogService;
import com.mytijian.wormhole.dao.dao.UserOperLogMapper;
import com.mytijian.wormhole.dao.model.UserOperLog;
import org.springframework.stereotype.Service;

/**
 * Created by wangchangpeng on 2017/5/9.
 */
@Service("userOperLogService")
public class UserOperLogServiceImpl extends ServiceImpl<UserOperLogMapper, UserOperLog> implements UserOperLogService {
}

package com.mytijian.wormhole.service.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mytijian.wormhole.dao.dao.AccessMapper;
import com.mytijian.wormhole.dao.model.Access;
import com.mytijian.wormhole.service.service.AccessService;
import org.springframework.stereotype.Service;

/**
 * Created by wangchangpeng on 2017/5/8.
 */
@Service("accessService")
public class AccessServiceImpl extends ServiceImpl<AccessMapper, Access> implements AccessService {

}

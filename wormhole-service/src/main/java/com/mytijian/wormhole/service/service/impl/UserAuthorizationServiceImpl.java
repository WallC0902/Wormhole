package com.mytijian.wormhole.service.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mytijian.wormhole.dao.constant.YesOrNo;
import com.mytijian.wormhole.service.constant.RequestType;
import com.mytijian.wormhole.service.service.UserAuthorizationService;
import com.mytijian.wormhole.dao.dao.UserAuthorizationMapper;
import com.mytijian.wormhole.dao.model.UserAuthorization;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangchangpeng on 2017/5/9.
 */
@Service("userAuthorizationService")
public class UserAuthorizationServiceImpl extends ServiceImpl<UserAuthorizationMapper, UserAuthorization> implements UserAuthorizationService {


    /**
     * 检查用户是否授权点单登录或者体检报告查询
     *
     * @param mid 第三方access_id
     * @param idCard 单点用户的身份证号码
     *
     * @return {true: 已授权; false: 未授权}
     */
    public boolean checkUserAuthoriz (String mid, String idCard, RequestType requestType) {

        Map columnMap = new HashMap<String, String>();
        columnMap.put("access_id", mid);
        columnMap.put("id_card", idCard);
        List<UserAuthorization> userAuthorizationList = selectByMap(columnMap);
        if (CollectionUtils.isEmpty(userAuthorizationList)) {
            return false;
        }
        UserAuthorization userAuthorization = userAuthorizationList.get(0);

        return checkUserAuth(userAuthorization, requestType);
    }

    /**
     *  根据检查类型确认是否授权
     * @param userAuthorization
     * @param requestType
     * @return
     */
    private boolean checkUserAuth (UserAuthorization userAuthorization,  RequestType requestType) {
        if (requestType.equals(RequestType.TYPE_SSO)) {
            if (userAuthorization.getIsSso().equals(YesOrNo.Y)) {
                return true;
            }
        }
        if (requestType.equals(RequestType.TYPE_QUERY_MEDICAL_REPORT)) {
            if (userAuthorization.getIsMedicalReport().equals(YesOrNo.Y)) {
                return true;
            }
        }

        return false;
    }




}

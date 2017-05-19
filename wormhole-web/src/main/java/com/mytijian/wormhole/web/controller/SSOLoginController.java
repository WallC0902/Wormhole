package com.mytijian.wormhole.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mytijian.wormhole.base.constant.Constants;
import com.mytijian.wormhole.base.constant.UserAuthEnum;
import com.mytijian.wormhole.base.dto.UserAuthDO;
import com.mytijian.wormhole.dao.constant.YesOrNo;
import com.mytijian.wormhole.dao.model.UserAuthorization;
import com.mytijian.wormhole.service.constant.RequestType;
import com.mytijian.wormhole.service.constant.WormholeResultCode;
import com.mytijian.wormhole.service.exception.WormholeException;
import com.mytijian.wormhole.service.service.UserAuthorizationService;
import com.mytijian.wormhole.service.service.UserSSOLoginService;
import com.mytijian.wormhole.web.domain.DecryptBO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.mytijian.wormhole.web.util.ControllerUtil.processResult;
import static com.mytijian.wormhole.web.util.ControllerUtil.bulidUrl;
/**
 * Created by wangchangpeng on 2017/5/16.
 */
@RestController
public class SSOLoginController {

    @Autowired
    private UserAuthorizationService userAuthorizationService;

    private UserSSOLoginService userSSOLoginService;

    /**
     * 单点登录接口
     *
     * @param decryptBO
     * @return
     */
    @PostMapping("/sso/login")
    public String ssoLogin (DecryptBO decryptBO) {

        boolean flag = userAuthorizationService.checkUserAuthoriz(
                decryptBO.getMid(),
                decryptBO.getIdCard(),
                RequestType.TYPE_SSO
        );

        if (!flag) {
            UserAuthDO userAuthDO = new UserAuthDO(UserAuthEnum.SSO_USER_AUTH_URL);
            return processResult(userAuthDO);
        }

        return login(decryptBO);
    }


    /**
     * 用户授权确认单点登录
     *
     * @param decryptBO
     * @return
     */
    @PostMapping("/sso/user/auth")
    public String userAuthSSO (DecryptBO decryptBO) {
        if (decryptBO == null) {
            throw new WormholeException(WormholeResultCode.SSO_AUTH_FAIL_NO_MEAN);
        }

        List<UserAuthorization> userAuthList = userAuthorizationService.selectList(new EntityWrapper<UserAuthorization>()
                .eq("access_id", decryptBO.getMid())
                .eq("id_card", decryptBO.getIdCard())
        );
        if (CollectionUtils.isEmpty(userAuthList)) {
            UserAuthorization userAuthorization = new UserAuthorization();
            userAuthorization.setAccessId(Integer.valueOf(decryptBO.getMid()));
            userAuthorization.setIdCard(decryptBO.getIdCard());
            userAuthorization.setName(decryptBO.getName());
            userAuthorization.setMobile(decryptBO.getMobile());
            userAuthorization.setGender(decryptBO.getGender());
            userAuthorization.setMarriageStatus(decryptBO.getMarriageStatus());
            userAuthorization.setIsSso(YesOrNo.Y);
            userAuthorization.setSsoDate(new Date());
            userAuthorization.setCreateDate(new Date());
            userAuthorization.setUpdateDate(new Date());
            userAuthorizationService.insert(userAuthorization);
        } else {
            UserAuthorization userAuthorization = userAuthList.get(0);
            if (userAuthorization.getIsSso().equals(YesOrNo.Y)){
                return login(decryptBO);
            }
            userAuthorization.setIsSso(YesOrNo.Y);
            userAuthorization.setSsoDate(new Date());
            userAuthorization.setUpdateDate(new Date());
            userAuthorizationService.updateById(userAuthorization);
        }

        return login(decryptBO);
    }


    private String login (DecryptBO decryptBO) {
        // todo 对接sso登录的功能，走dubbo服务
//        String sessionId = userSSOLoginService.ssoLogin();
        String sessionId = UUID.randomUUID().toString();
        if (StringUtils.isBlank(sessionId)) {
            throw new WormholeException(WormholeResultCode.SSO_AUTH_FAIL_TIMEOUT);
        }

        String url = bulidUrl(sessionId, decryptBO.getResRedirect());
        UserAuthDO userAuthDO = new UserAuthDO(UserAuthEnum.SSO_LOGIN.getStatus(), url);

        return processResult(userAuthDO);
    }

}

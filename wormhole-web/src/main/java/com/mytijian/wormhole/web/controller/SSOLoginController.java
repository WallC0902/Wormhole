package com.mytijian.wormhole.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mytijian.account.dto.WormholeRelateUserDto;
import com.mytijian.account.exceptions.LoginFailedException;
import com.mytijian.account.exceptions.RegisterException;
import com.mytijian.account.service.WormholeUserService;
import com.mytijian.wormhole.base.constant.UserAuthEnum;
import com.mytijian.wormhole.base.dto.UserAuthDO;
import com.mytijian.wormhole.base.tools.DataFormatValidate;
import com.mytijian.wormhole.dao.constant.YesOrNo;
import com.mytijian.wormhole.dao.model.UserAuthorization;
import com.mytijian.wormhole.service.constant.Constants;
import com.mytijian.wormhole.service.constant.RequestType;
import com.mytijian.wormhole.service.constant.WormholeResultCode;
import com.mytijian.wormhole.service.exception.WormholeException;
import com.mytijian.wormhole.service.service.UserAuthorizationService;
import com.mytijian.wormhole.web.domain.DecryptBO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static com.mytijian.wormhole.web.util.ControllerUtil.bulidUrl;
import static com.mytijian.wormhole.web.util.ControllerUtil.processResult;
/**
 * Created by wangchangpeng on 2017/5/16.
 */
@RestController
@RequestMapping("/sso")
public class SSOLoginController {

    private static Logger logger = LoggerFactory.getLogger(SSOLoginController.class);

    @Autowired
    private UserAuthorizationService userAuthorizationService;

    @Reference(version = "1.0.0.dingchentest")
    private WormholeUserService wormholeUserService;

    /**
     * 单点登录接口
     *
     * @return
     */
    @PostMapping("/login")
    public String ssoLogin (HttpServletRequest request) {

        DecryptBO decryptBO = (DecryptBO) request.getAttribute(Constants.ATTR_DECRYPT_BO);
        if (decryptBO == null) {
            logger.error("decryptBO is null");
            throw new WormholeException(WormholeResultCode.PARAM_ERROR);
        }
        if (StringUtils.isBlank(decryptBO.getMobile())) {
            logger.error("decryptBO.getMobile() is null");
            throw new WormholeException(WormholeResultCode.SSO_AUTH_FAIL_EMPTY_MOBILE);
        }
        if (!DataFormatValidate.vaildataMobile(decryptBO.getMobile())) {
            logger.error("decryptBO.getMobile() wrong format ");
            throw new WormholeException(WormholeResultCode.SSO_AUTH_FAIL_WRONG_MOBILE);
        }
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
    @PostMapping("/user/auth")
    public String userAuthSSO (DecryptBO decryptBO) {
        if (decryptBO == null) {
            logger.error("decryptBO is null");
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


    /**
     * SSO用户关联登录
     *
     * @param decryptBO
     * @return
     */
    private String login (DecryptBO decryptBO) {

        WormholeRelateUserDto wormholeRelateUserDto = new WormholeRelateUserDto();

        String sessionId = null;
        try {
            sessionId = wormholeUserService.relate3rdUser(wormholeRelateUserDto);
        } catch (RegisterException e) {

            logger.error("第三方SSO用户，注册失败" + e.getMessage());
            throw new WormholeException(WormholeResultCode.SSO_AUTH_FAIL_REGISTER_ERROR);
        } catch (LoginFailedException e) {

            logger.error("第三方SSO用户，登录失败" + e.getMessage());
            throw new WormholeException(WormholeResultCode.LOGIN_ERROR);
        }
        if (StringUtils.isBlank(sessionId)) {
            logger.error("第三方SSO用户，登录失败，返回sessionId为空");
            throw new WormholeException(WormholeResultCode.SSO_AUTH_FAIL_TIMEOUT);
        }

        String url = bulidUrl(sessionId, decryptBO.getResRedirect());
        UserAuthDO userAuthDO = new UserAuthDO(UserAuthEnum.SSO_LOGIN.getStatus(), url);

        return processResult(userAuthDO);
    }

}

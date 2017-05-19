package com.mytijian.wormhole.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mytijian.wormhole.base.constant.Constants;
import com.mytijian.wormhole.dao.constant.StatusEnum;
import com.mytijian.wormhole.dao.model.Access;
import com.mytijian.wormhole.dao.model.UserOperLog;
import com.mytijian.wormhole.service.constant.WormholeResultCode;
import com.mytijian.wormhole.service.exception.WormholeException;
import com.mytijian.wormhole.service.service.AccessService;
import com.mytijian.wormhole.service.service.UserOperLogService;
import com.mytijian.wormhole.web.domain.DecryptBO;
import com.mytijian.wormhole.web.util.EncryptUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * 实现主要的拦截器逻辑实现
 *
 * Created by wangchangpeng on 2017/5/11.
 */
@Configuration
public class UserAuthInterceptor implements HandlerInterceptor{

    private Logger logger = LoggerFactory.getLogger(UserAuthInterceptor.class);

    private static final Long INTERVALS = 3000L;

    @Autowired
    private AccessService accessService;

    @Autowired
    private UserOperLogService userOperLogService;
    /*public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                                     Object handler) throws Exception {

            }
        }).addPathPatterns("/api*//*");
    }*/

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        DecryptBO decryptBO = relaseParam(request);
        if (decryptBO == null) {
            logger.info("信息获取转换失败");
            throw new WormholeException(WormholeResultCode.SSO_AUTH_FAIL_NO_MEAN);
        }
        checkEmpty(decryptBO);
        Access access = accessService.selectById(decryptBO.getMid());
        if (access == null) {
            logger.info("第三方不存在，mid: " + decryptBO.getMid());
            throw new WormholeException(WormholeResultCode.SSO_AUTH_FAIL_MID_NOT_EXIT);
        }
        if (!access.getStatus().equals(StatusEnum.NORMAL)) {
            logger.info("第三方状态不正常，mid: " + decryptBO.getMid());
            throw new WormholeException(WormholeResultCode.SSO_AUTH_FAIL_INVALID);
        }

        if (!verifySign(decryptBO, access.getPublicKey())) {
            logger.info("检验签名失败，mid: " + decryptBO.getMid());
            throw new WormholeException(WormholeResultCode.SSO_AUTH_FAIL_UMAMTCH);
        }
        checkTimeStamp(decryptBO);

        request.setAttribute("decryptBO", decryptBO);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object o, Exception e) throws Exception {

    }


    /**
     * 从HttpServletRequest中读取请求参数
     *
     * @param request
     * @return
     * @throws IOException
     */
    private DecryptBO relaseParam (HttpServletRequest request) {
        BufferedReader in= null;
        try {
            in = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            return JSONObject.parseObject(line, DecryptBO.class);
        } catch (IOException e) {
            logger.info("信息获取转换失败: " + e.getMessage());
            throw new WormholeException(WormholeResultCode.SSO_AUTH_FAIL_NO_MEAN);
        }
    }


    /**
     * 检查必甜项
     *
     * @param decryptBO
     */
    private void checkEmpty (DecryptBO decryptBO) {
        if (decryptBO == null) {
            logger.debug("认证信息：mid为空");
            throw new WormholeException(WormholeResultCode.PARAM_ERROR);
        }

        String mid = decryptBO.getMid();
        if (StringUtils.isBlank(mid)) {
            logger.debug("认证信息：mid为空");
            throw new WormholeException(WormholeResultCode.SSO_AUTH_FAIL_EMPTY_MID);
        }
        String signature = decryptBO.getSignature();
        if (StringUtils.isBlank(signature)) {
            logger.debug("认证信息：signature为空");
            throw new WormholeException(WormholeResultCode.SSO_AUTH_FAIL_EMPTY_SIGNATURE);
        }
        String name = decryptBO.getName();
        if (StringUtils.isBlank(name)) {
            logger.debug("认证信息：name为空");
            throw new WormholeException(WormholeResultCode.SSO_AUTH_FAIL_EMPTY_NAME);
        }
        String idCard = decryptBO.getIdCard();
        if (StringUtils.isBlank(idCard)) {
            logger.debug("认证信息：id_card为空");
            throw new WormholeException(WormholeResultCode.SSO_AUTH_FAIL_EMPTY_ID_CARD);
        }
        /*String mobile = decryptBO.getMobile();
        if (StringUtils.isBlank(mobile)) {
            throw new WormholeException(WormholeResultCode.SSO_AUTH_FAIL_EMPTY_MOBILE);
        }*/
    }

    /**
     * 验证签名是否一致
     *
     * @param decryptBO
     * @param publicKey
     * @return
     */
    private boolean verifySign (DecryptBO decryptBO, String publicKey) {
        JSONObject json = new JSONObject(true);
        json.put(Constants.M_ID, decryptBO.getMid());
        json.put(Constants.TIME_STAMP, decryptBO.getTimeStamp());
        json.put(Constants.NAME, decryptBO.getName());
        json.put(Constants.ID_CARD, decryptBO.getIdCard());
        json.put(Constants.MOBILE, decryptBO.getMobile());

        if (!StringUtils.isBlank(decryptBO.getGender())) {
            json.put(Constants.GENDER, decryptBO.getGender());
        }
        if (!StringUtils.isBlank(decryptBO.getMarriageStatus())) {
            json.put(Constants.MARRIAGE_STATUS, decryptBO.getMarriageStatus());
        }
        if (decryptBO.getResRedirect() != null) {
            json.put(Constants.RES_REDIRECRT, decryptBO.getResRedirect());
        }
        String data = json.toJSONString();
        try {
            return EncryptUtil.verify(data, publicKey, decryptBO.getSignature());
        } catch (Exception e) {
            logger.error("认证信息解密密失败");
            throw new WormholeException(WormholeResultCode.SSO_AUTH_FAIL_DECRYPT);
        }
    }

    /**
     * 检查与上次请求的时间间隔，是否符合规定时间值
     *
     * @param decryptBO
     * @return
     */
    private boolean checkTimeStamp (DecryptBO decryptBO) {
        List<UserOperLog> userOperLogList = userOperLogService.selectList(new EntityWrapper<UserOperLog>()
                .eq("access_id", decryptBO.getMid())
                .eq("id_card", decryptBO.getIdCard())
                .orderBy("oper_key", true)
        );
        if (CollectionUtils.isEmpty(userOperLogList)) {
            return true;
        }

        UserOperLog userOperLog = userOperLogList.get(0);
        // 检查两次请求时间值，如果最近一次时间值小于或者等于之前的值，则链接失效，直接返回
        if (userOperLog.getOperKey() >= decryptBO.getTimeStamp()) {
            logger.info("请求时间戳小于或者等于之前的请求，直接返回");
            throw new WormholeException(WormholeResultCode.SSO_AUTH_FAIL_INVALID_MSG);
        }

        // 检查两次请求时间间隔，如果小于INTERVALS值，直接返回
        if (decryptBO.getTimeStamp() - userOperLog.getOperKey() <= INTERVALS) {
            logger.info("对用户维度的流控，最近两次请求时间差，小于设置的值。直接返回重试");
            throw new WormholeException(WormholeResultCode.SSO_AUTH_FAIL_TIMEOUT);
        }

        return true;
    }

}

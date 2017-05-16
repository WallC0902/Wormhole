package com.mytijian.wormhole.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mytijian.wormhole.base.constant.Constants;
import com.mytijian.wormhole.base.tools.AbstractEncrypt;
import com.mytijian.wormhole.dao.model.Access;
import com.mytijian.wormhole.dao.model.UserOperLog;
import com.mytijian.wormhole.service.constant.RequestType;
import com.mytijian.wormhole.service.constant.WormholeResultCode;
import com.mytijian.wormhole.service.exception.WormholeException;
import com.mytijian.wormhole.service.service.AccessService;
import com.mytijian.wormhole.service.service.UserOperLogService;
import com.mytijian.wormhole.web.util.DecryptUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

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
public class UserAuthInterceptor extends WebMvcConfigurerAdapter{

    @Autowired
    private AccessService accessService;

    @Autowired
    private UserOperLogService userOperLogService;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                                     Object handler) throws Exception {

                JSONObject jsonObject = relaseParam(request);
                if (jsonObject == null) {
                    throw new WormholeException(WormholeResultCode.PARAM_ERROR);
                }

                String mid = jsonObject.getString(Constants.M_ID);
                if (StringUtils.isBlank(mid)) {
                    throw new WormholeException(WormholeResultCode.SSO_AUTH_FAIL_EMPTY_MID);
                }
                String secret = jsonObject.getString(Constants.SECRET);
                if (StringUtils.isBlank(secret)) {
                    throw new WormholeException(WormholeResultCode.SSO_AUTH_FAIL_EMPTY_SECRET);
                }
                String key = jsonObject.getString(Constants.KEY);
                if (StringUtils.isBlank(key)) {
                    throw new WormholeException(WormholeResultCode.SSO_AUTH_FAIL_EMPTY_KEY);
                }
                String mobile = jsonObject.getString(Constants.MOBILE);
                if (StringUtils.isBlank(mobile)) {
                    throw new WormholeException(WormholeResultCode.SSO_AUTH_FAIL_EMPTY_MOBILE);
                }

                return check3rdAuthoriz(mid, secret, key);
            }
        }).addPathPatterns("/api/*");
    }

    /**
     * 从HttpServletRequest中读取请求参数
     *
     * @param request
     * @return
     * @throws IOException
     */
    private JSONObject relaseParam (HttpServletRequest request) throws IOException {
        BufferedReader in=new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        return JSONObject.parseObject(line);
    }


    /**
     * 检查第三方机构是否合法，以及操作key是否正常
     *
     * @param mid
     * @param secret
     * @param key
     * @return
     */
    private boolean check3rdAuthoriz (String mid, String secret, String key) throws Exception{

        Access access = accessService.selectById(Integer.valueOf(mid));
        if (access == null) {
            throw new WormholeException(WormholeResultCode.UNKNOWN);
        }

        String decryptMsg = DecryptUtil.decryptPBE(
                    AbstractEncrypt.decryptBase64(secret),
                    access.getToken(),
                    AbstractEncrypt.decryptBase64(key)
            ).toString();

        if (StringUtils.isBlank(decryptMsg)) {
            throw new WormholeException(WormholeResultCode.SSO_AUTH_FAIL_DECRYPT);
        }
        String[] data = decryptMsg.split("&&");
        if (data.length != 3) {
            throw new WormholeException(WormholeResultCode.SSO_AUTH_FAIL_UMAMTCH);
        }
        String idCard = data[1];
        String operKey = data[2];

        //TODO 做身份证格式合法的验证
        List<UserOperLog> userOperLogs = userOperLogService.selectList(
                new EntityWrapper<UserOperLog>()
                        .eq("access_id", mid)
//                        .eq("type", getRequestType())
                        .eq("oper_key", operKey)
                        .eq("id_card", idCard)
        );

        if (!CollectionUtils.isEmpty(userOperLogs)) {
            throw new WormholeException(WormholeResultCode.SSO_AUTH_FAIL_INVALID);
        }

        return true;
    }

    /**
     * 获取操作类型：
     * {
     *       TYPE_SSO("单点登录"),
     *       TYPE_QUERY_ORDER("订单查询"),
     *       TYPE_REPORT("体检报告查询");
     * }
     * @return
     */
//    private RequestType getRequestType(){
//        return null;
//    }

}

package com.mytijian.wormhole.web.interceptor;

import com.mytijian.wormhole.service.constant.RequestType;
import org.springframework.context.annotation.Configuration;

/**
 * 单点登录拦截器，检查是否授权单点登录：
 *      {
 *          是：直接单点登录到每天健康，下单；
 *          否：跳出授权页面
 *      }
 *  更多详细的实现，可以去 {@link AbstractBaseInterceptor}
 * Created by wangchangpeng on 2017/5/11.
 */
@Configuration
public class SsoLoginInterceptor extends AbstractBaseInterceptor {

    @Override
    public String handler() {
        //TODO 具体的登录实现，返回sessionId
        return null;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.TYPE_SSO;
    }

    @Override
    public String getPathPattern() {
        return "/sso/*";
    }
}

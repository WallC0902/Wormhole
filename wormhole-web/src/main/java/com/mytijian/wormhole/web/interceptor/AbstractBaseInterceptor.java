package com.mytijian.wormhole.web.interceptor;

import com.mytijian.wormhole.service.constant.RequestType;
import com.mytijian.wormhole.service.service.UserAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.mytijian.wormhole.dao.constant.YesOrNo;
import com.mytijian.wormhole.dao.model.UserAuthorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 拦截器抽象类，主要的拦截器逻辑实现
 * 更多业务信息，请参考 {@link SsoLoginInterceptor} 或者 {@link MedicalReportInterceptor}
 *
 * Created by wangchangpeng on 2017/5/11.
 */
public abstract class AbstractBaseInterceptor extends WebMvcConfigurerAdapter {

    @Autowired
    private UserAuthorizationService userAuthorizationService;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                                     Object handler) throws Exception {
                String mid = request.getParameter("mid");
                String token = request.getParameter("token");
                if (StringUtils.isEmpty(mid) || StringUtils.isEmpty(token)) {
                    return false;
                }

                if (!checkUserAuthoriz(mid, token)) {
                    // TODO 弹出授权页面，让用户选择是否授权
                }
                handler();

                return true;
            }
        }).addPathPatterns(getPathPattern());
    }

    /**
     * 检查用户是否授权点单登录或者体检报告查询
     *
     * @param mid 第三方access_id
     * @param token 第三方加密后的token
     *
     * @return {true: 已授权; false: 未授权}
     */
    private boolean checkUserAuthoriz (String mid, String token) {

        // TODO 解密出用户身份证号码
        String idCard = token;

        Map columnMap = new HashMap<String, String>();
        columnMap.put("access_id", mid);
        columnMap.put("id_card", idCard);
        List<UserAuthorization> userAuthorizationList = userAuthorizationService.selectByMap(columnMap);
        if (CollectionUtils.isEmpty(userAuthorizationList)) {
            return false;
        }
        UserAuthorization userAuthorization = userAuthorizationList.get(0);
        YesOrNo flag = YesOrNo.N;
        if (getRequestType().equals(RequestType.TYPE_SSO)) {
            flag = userAuthorization.getIsSso();
        }
        if (getRequestType().equals(RequestType.TYPE_REPORT)) {
            flag = userAuthorization.getIsMedicalReport();
        }
        if (flag.equals(YesOrNo.Y)) {
            return true;
        }
        return false;
    }

    /**
     * 已经已经确认授权后，各自的业务实现
     *
     * @return
     */
    public abstract String handler();

    /**
     * 抽象方法，返回请求的接口类型
     *
     * @return RequestType
     */
    public abstract RequestType getRequestType();

    /**
     * 抽象方法，返回拦截的路径
     *
     * @return
     */
    public abstract String getPathPattern();

}

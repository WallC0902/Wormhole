package com.mytijian.wormhole.web.handler;

import com.alibaba.fastjson.JSON;
import com.mytijian.wormhole.service.constant.WormholeResultCode;
import com.mytijian.wormhole.web.dto.ResultDTO;
import com.mytijian.wormhole.service.service.UserOperLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;
import com.mytijian.wormhole.dao.constant.YesOrNo;
import com.mytijian.wormhole.dao.model.UserOperLog;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 操作log拦截记录handler
 *
 * Created by wangchangpeng on 2017/5/11.
 */
@Aspect
@Component
public class GlobalOperLogHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalOperLogHandler.class);

    private ThreadLocal<UserOperLog> threadLocal = new ThreadLocal<>();

    @Pointcut("execution(* com.mytijian.wormhole.web.controller..*.*(..))")
    public void webRequestLog(){}

    @Autowired
    private UserOperLogService userOperLogService;

    /**
     * 前置通知，方法调用前被调用
     * @param joinPoint
     */
    @Around("webRequestLog()")
    public void doBeforeAdvice(JoinPoint joinPoint){
        try {

            long beginTime = System.currentTimeMillis();

            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String beanName = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            String uri = request.getRequestURI();
            String remoteAddr = getIpAddr(request);
            String method = request.getMethod();
            String params = "";
            if ("POST".equals(method)) {
                Object[] paramsArray = joinPoint.getArgs();
                params = argsArrayToString(paramsArray);
            } else {
                Map<?, ?> paramsMap = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
                params = paramsMap.toString();
            }

            logger.debug("uri=" + uri + "; beanName=" + beanName + "; remoteAddr=" + remoteAddr
                    + "; methodName=" + methodName + "; params=" + params);

            UserOperLog optLog = new UserOperLog();
//            optLog.setBeanName(beanName);
            optLog.setType(uri);
//            optLog.setCurUser(user);
            optLog.setIpAddr(remoteAddr);
            optLog.setRequestTime(beginTime);
            optLog.setOperDate(new Date());
            threadLocal.set(optLog);

        } catch (Exception e) {
            logger.error("webRequestLog doBefore() error: ", e);
        }
    }

    /**
     * 后置最终通知（目标方法只要执行完了就会执行后置通知方法）
     * @param result
     */
    @AfterReturning(returning = "result", pointcut = "webRequestLog()")
    public void doAfterReturning(Object result) {
        try {
            // 处理完请求，返回内容
            UserOperLog optLog = threadLocal.get();
            ResultDTO resultDTO = (ResultDTO) result;
            if (resultDTO.getCode().equals(WormholeResultCode.SUCCESS.getCode())) {
                optLog.setSuccess(YesOrNo.Y);
            } else {
                optLog.setSuccess(YesOrNo.N);
                optLog.setExceptionInfo(JSON.toJSONString(result));
            }
            long beginTime = optLog.getRequestTime();
            long requestTime = (System.currentTimeMillis() - beginTime);
            optLog.setRequestTime(requestTime);
            userOperLogService.insert(optLog);
        } catch (Exception e) {
            logger.error("webRequestLog doAfterReturning() error: ", e);
        }
    }

    /**
     * 获取登录用户远程主机ip地址
     *
     * @param request
     * @return
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 请求参数拼装
     *
     * @param paramsArray
     * @return
     */
    private String argsArrayToString(Object[] paramsArray) {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0) {
            for (int i = 0; i < paramsArray.length; i++) {
                Object jsonObj = JSON.toJSON(paramsArray[i]);
                params += jsonObj.toString() + " ";
            }
        }
        return params.trim();
    }
}

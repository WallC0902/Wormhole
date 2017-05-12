package com.mytijian.wormhole.sso.client;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangchangpeng on 2017/5/12.
 */
public class WormholeSsoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
                             Object o) throws Exception {

//        req.getRequestDispatcher("www.baidu.com").forward(req, res);
        res.sendRedirect("www.baidu.com");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res,
                                Object o, Exception e) throws Exception {

    }
}

package com.mytijian.wormhole.web.interceptor;

import com.mytijian.wormhole.service.constant.RequestType;
import org.springframework.context.annotation.Configuration;

/**
 * 体检报告拦截器，检查是否授权拉取体检报告：
 * {
 *     是：直接查询拉取体检报告；
 *     否：跳转到授权页面
 * }
 * 更多详细的实现，可以去 {@link AbstractBaseInterceptor}
 *
 * Created by wangchangpeng on 2017/5/11.
 */
@Configuration
public class MedicalReportInterceptor extends AbstractBaseInterceptor {

    @Override
    public String handler() {
        // do nothing
        return null;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.TYPE_REPORT;
    }

    @Override
    public String getPathPattern() {
        return "/query/medical/report";
    }
}

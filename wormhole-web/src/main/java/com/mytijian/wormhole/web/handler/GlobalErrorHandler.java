package com.mytijian.wormhole.web.handler;

import com.mytijian.wormhole.web.dto.ResultDTO;
import com.mytijian.wormhole.service.exception.WormholeException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一错误码异常处理
 *
 * Created by wangchangpeng on 2017/05/09.
 */
@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(value = WormholeException.class)
    public ResultDTO errorHandlerOverJson(HttpServletRequest request,
                                          WormholeException exception) {
        ResultDTO result = new ResultDTO(exception.getCode(), exception.getMessage());
        return result;
    }
}

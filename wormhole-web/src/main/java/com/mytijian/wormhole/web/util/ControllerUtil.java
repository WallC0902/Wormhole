package com.mytijian.wormhole.web.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mytijian.wormhole.base.constant.Constants;
import com.mytijian.wormhole.base.constant.ResRedirectEnum;
import com.mytijian.wormhole.service.constant.WormholeResultCode;
import com.mytijian.wormhole.web.dto.ResultDTO;
import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Encoder;

/**
 *
 * Created by wangchangpeng on 2017/5/16.
 */
public class ControllerUtil {

    private static final String RESULT_NULL = JSON.toJSONString(new ResultDTO(null));
    private static final String RESULT_UNKNOWN = JSON.toJSONString(
            new ResultDTO(
                    WormholeResultCode.UNKNOWN.getCode(),
                    WormholeResultCode.UNKNOWN.getMessage()
            )
    );

    public static String processResult(Object data) {
        if (data == null) {
            return RESULT_NULL;
        }

        ResultDTO resultDTO = new ResultDTO(data);
        return JSON.toJSONString(resultDTO);
    }

    public static String processResult(String name, Object object) {
        if (StringUtils.isBlank(name) || object == null) {
            return null;
        }
        JSONObject data = new JSONObject();
        data.put(name, object);

        return processResult(data);
    }

    public static String processErrorResult(String errCode, String errMsg) {
        ResultDTO resultDTO = new ResultDTO(errCode, errMsg);
        return JSON.toJSONString(resultDTO);
    }

    public static String bulidUrl(String sessionId, ResRedirectEnum resRedirectEnum) {
        return Constants.MT_REDIRECT
                + "?token="
                + new BASE64Encoder().encodeBuffer(sessionId.getBytes())
                + "redirect=" + Constants.MT_HTTP + resRedirectEnum.getUrl();
    }
}

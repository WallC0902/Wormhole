package com.mytijian.wormhole.sdk.sso.executer;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.mytijian.wormhole.sdk.executer.BaseExecutor;
import com.mytijian.wormhole.sdk.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangchangpeng on 2017/5/22.
 */
public class SSOLoginExecuter extends BaseExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SSOLoginExecuter.class);


    public String ssoLogin(JSONObject requestData, String requestUrl, String privateKey){

        LOGGER.debug("requestData:[" + requestData.toJSONString() + "]");
        String responseStr = HttpClientUtil.post(requestUrl, requestData.toJSONString());

        LOGGER.debug("responseStr:[" + responseStr + "]");

//        JSONObject responseData = JSONObject.parseObject(responseStr, new Feature[] { Feature.SortFeidFastMatch });
//        verifySignature(responseData, privateKey);
        return responseStr;
    }

    public String buildOtherMsg (JSONObject responseData){

        return null;
    }

}

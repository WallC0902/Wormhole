package com.mytijian.wormhole.sdk.executer;

import com.alibaba.fastjson.JSONObject;
import com.mytijian.wormhole.base.constant.Constants;
import com.mytijian.wormhole.sdk.exception.WormholeException;
import com.mytijian.wormhole.sdk.util.EncryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangchangpeng on 2017/5/22.
 */
public abstract class BaseExecutor {

    static final Logger LOGGER = LoggerFactory.getLogger(BaseExecutor.class);

    /**
     * 检测
     * @return
     */
    public boolean verifySignature (JSONObject responseData, String privateKey) {

        String data = buildOtherMsg(responseData);

        String signature = responseData.getString(Constants.SIGNATURE);
        try {
            return EncryptUtil.verify(data, privateKey, signature);
        } catch (Exception e) {
            LOGGER.error("verifySignature verify error: " + e.getMessage());
            throw new WormholeException("verifySignature verify error: " + e.getMessage(), e);
        }
    }


    private JSONObject buildBaseMsg (JSONObject responseData) {
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put(Constants.M_ID, responseData.getString(Constants.M_ID));
        jsonObject.put(Constants.NAME, responseData.getString(Constants.NAME));
        jsonObject.put(Constants.ID_CARD, responseData.getString(Constants.ID_CARD));

        return jsonObject;
    }

    public abstract String buildOtherMsg (JSONObject responseData);

}

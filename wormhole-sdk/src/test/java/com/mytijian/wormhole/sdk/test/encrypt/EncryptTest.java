package com.mytijian.wormhole.sdk.test.encrypt;

import com.alibaba.fastjson.JSONObject;
import com.mytijian.wormhole.base.constant.ResRedirectEnum;
import com.mytijian.wormhole.sdk.sso.budiler.SSOBuilder;

/**
 * Created by wangchangpeng on 2017/5/16.
 */
public class EncryptTest {

    public static void main(String args[]) {
        SSOBuilder ssoBuilder = new SSOBuilder();
        ssoBuilder.setName("安涵菡");
        ssoBuilder.setIdCard("410482198804138128");
        ssoBuilder.setMobile("18099996666");
        ssoBuilder.setGender("0");
        ssoBuilder.setMarriageStatus("1");
        ssoBuilder.setResRedirect(ResRedirectEnum.INDEX);
        JSONObject jsonObject = null;
        try {
            jsonObject = ssoBuilder.build("1", "ASDQWE123JH1L234HK23J4H2K34K12J3H");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("jsonObject: " + jsonObject.toJSONString());
    }
}

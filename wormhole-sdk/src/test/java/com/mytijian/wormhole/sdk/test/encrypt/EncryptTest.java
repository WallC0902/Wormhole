package com.mytijian.wormhole.sdk.test.encrypt;

import com.alibaba.fastjson.JSONObject;
import com.mytijian.wormhole.base.constant.ResRedirectEnum;
import com.mytijian.wormhole.sdk.sso.builder.SSOBuilder;

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
            jsonObject = ssoBuilder.build(13001, "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAorCetixm2GM86ZJkLbI3IRlxmO1E4QEg7Jxnp5P6ny7hilinBYnwMUvAxTQsMg6IumROJ9SvdtnUVoYG19b5AwIDAQABAkEAg4IhyUC7Ic+WPXiIYifdSHQ4z1V8iCVRZ/JGF8AWpvUBcpb4Wb8Mx9qzH+0Eo6jSFbVtQ7BE0vF77judNEcCAQIhAOoJ4hxCZM2je8XV+OXsy4VsUykhicvebpU/Z3Qa1qpxAiEAsfTJOC7kAGxf+Hx9qyrUzpTG09+5Bq6n8lCbiosIjLMCIQDUIFsiNAd73+cKvNNZS1R4vWNuz2g/MX/XpN1W/hB98QIgcBZNs4ydYcLhRAMtqGVm+uNsxMESqqnR8OdiwQXHkc8CIHXMELHQjV2tQCbacqyDcvwuqphQqaggJff7XtobGJ1p");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("jsonObject: " + jsonObject.toJSONString());
    }
}

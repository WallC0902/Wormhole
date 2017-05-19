package com.mytijian.wormhole.web.util;

import com.mytijian.wormhole.base.tools.RSA;

/**
 * Created by wangchangpeng on 2017/5/15.
 */
public class EncryptUtil {

    /**
     * 给数据加签名
     *
     * @param data
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static String sign(String data, String publicKey) throws Exception {
        return RSA.sign(data.getBytes(), publicKey);
    }

    /**
     * 验证签名
     *
     * @param data
     * @param publicKey
     * @param signatrue
     * @return
     * @throws Exception
     */
    public static boolean verify(String data, String publicKey, String signatrue) throws Exception {
        return RSA.verify(data.getBytes(), publicKey, signatrue);
    }

}

package com.mytijian.wormhole.sdk.util;

import com.mytijian.wormhole.base.tools.RSA;
import com.mytijian.wormhole.sdk.exception.WormholeException;

/**
 * Created by wangchangpeng on 2017/5/15.
 */
public class EncryptUtil {

    /**
     * 给数据加签名
     *
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String sign(String data, String privateKey) throws Exception {
        return RSA.sign(data.getBytes(), privateKey);
    }

    /**
     * 验证签名
     *
     * @param data
     * @param privateKey
     * @param signatrue
     * @return
     * @throws Exception
     */
    public static boolean verify(String data, String privateKey, String signatrue) throws Exception {
        return RSA.verify(data.getBytes(), privateKey, signatrue);
    }

}

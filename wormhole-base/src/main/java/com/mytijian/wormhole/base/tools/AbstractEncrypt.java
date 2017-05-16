package com.mytijian.wormhole.base.tools;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.IOException;
import java.security.Key;

/**
 *
 * Created by wangchangpeng on 2017/5/15.
 */
public abstract class AbstractEncrypt {

    /**
     * 定义加密方式
     * 支持以下任意一种算法
     * <p/>
     * <pre>
     * PBEWithMD5AndDES
     * PBEWithMD5AndTripleDES
     * PBEWithSHA1AndDESede
     * PBEWithSHA1AndRC2_40
     * </pre>
     */
    public final static String KEY_PBE = "PBEWITHMD5andDES";

    public final static int SALT_COUNT = 100;


    /**
     * 转换密钥
     *
     * @param key 字符串
     * @return
     */
    public static Key stringToKey(String key) throws Exception{
        SecretKey secretKey = null;
        PBEKeySpec keySpec = new PBEKeySpec(key.toCharArray());
        SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_PBE);
        secretKey = factory.generateSecret(keySpec);
        return secretKey;
    }


    /**
     * BASE64 加密
     *
     * @param key 需要加密的字节数组
     * @return 字符串
     * @throws Exception
     */
    public static String encryptBase64(byte[] key) throws IOException {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * BASE64 解密
     *
     * @param key 解密的字节数组
     * @return 字符串
     * @throws Exception
     */
    public static String decryptBase64ToString(String key) throws IOException {
        return decryptBase64(key).toString();
    }


    /**
     * BASE64 解密
     *
     * @param key 解密的字节数组
     * @return 字符串
     * @throws Exception
     */
    public static byte[] decryptBase64(String key) throws IOException {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

}

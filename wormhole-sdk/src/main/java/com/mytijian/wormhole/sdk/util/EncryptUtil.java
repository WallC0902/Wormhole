package com.mytijian.wormhole.sdk.util;

import com.mytijian.wormhole.sdk.dto.UserSsoDTO;
import com.mytijian.wormhole.base.tools.AbstractEncrypt;
import org.apache.commons.lang.RandomStringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;
import java.util.Random;

/**
 * Created by wangchangpeng on 2017/5/15.
 */
public class EncryptUtil extends AbstractEncrypt {

    private static  byte[] salt;

    public EncryptUtil() {
        init();
    }

    /**
     * 初始化盐（salt）
     *
     * @return
     */
    public byte[] init() {
        salt = new byte[8];
        Random random = new Random();
        random.nextBytes(salt);
        return salt;
    }


    public static String encrypt(String token, String name, String idCard) throws Exception {
        String assembl = name + "&& " + idCard + "&&" + RandomStringUtils.randomAlphabetic(5);
        byte[] encrypt = encryptPBE(assembl.getBytes(), token);

        return encryptBase64(encrypt);
    }

    /**
     * PBE 加密
     *
     * @param data 需要加密的字节数组
     * @param key  密钥
     * @return
     */
    static byte[] encryptPBE(byte[] data, String key) throws Exception {
        byte[] bytes = null;
        // 获取密钥
        Key k = stringToKey(key);
        PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, SALT_COUNT);
        Cipher cipher = Cipher.getInstance(KEY_PBE);
        cipher.init(Cipher.ENCRYPT_MODE, k, parameterSpec);
        bytes = cipher.doFinal(data);
        return bytes;
    }

    public static String getSalt() throws Exception {
        return encryptBase64(salt);
    }
}

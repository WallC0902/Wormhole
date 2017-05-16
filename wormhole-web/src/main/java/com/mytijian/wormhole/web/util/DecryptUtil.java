package com.mytijian.wormhole.web.util;

import com.mytijian.wormhole.base.tools.AbstractEncrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;

/**
 * Created by wangchangpeng on 2017/5/15.
 */
public class DecryptUtil extends AbstractEncrypt{

    /**
     * PBE 解密
     *
     * @param data 需要解密的字节数组
     * @param key  密钥
     * @param salt 盐
     * @return
     */
    public static byte[] decryptPBE(byte[] data, String key, byte[] salt) throws Exception {
        byte[] bytes = null;
        // 获取密钥
        Key k = stringToKey(key);
        PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, SALT_COUNT);
        Cipher cipher = Cipher.getInstance(KEY_PBE);
        cipher.init(Cipher.DECRYPT_MODE, k, parameterSpec);
        bytes = cipher.doFinal(data);
        return bytes;
    }
}

package com.mytijian.wormhole.base.test;

import com.mytijian.wormhole.base.tools.RSA;

import java.util.Map;

/**
 * Created by wangchangpeng on 2017/5/22.
 */
public class RSATests {


    public static void main (String[] args) {
        try {
            Map<String, Object> map =  RSA.genKeyPair();
            String privateKey = RSA.getPrivateKey(map);
            String publicKey = RSA.getPublicKey(map);

            System.out.println("privateKey : " + privateKey);
            System.out.println("publicKey : " + publicKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

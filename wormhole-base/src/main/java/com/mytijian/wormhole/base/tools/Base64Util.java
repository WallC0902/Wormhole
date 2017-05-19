package com.mytijian.wormhole.base.tools;


import com.mytijian.wormhole.base.tools.base64.Base64;

/**
 * <p>
 * BASE64编码解码工具包
 * </p>
 * <p>
 * 依赖bcprov-jdk14-1.48.jar
 * </p>
 * 
 * @author hubin
 * @Date 2014-6-17
 */
public class Base64Util {

	/**
	 * <p>
	 * BASE64字符串解码为二进制数据
	 * </p>
	 * 
	 * @param base64
	 * @return
	 * @throws Exception
	 */
	public static byte[] decode(String base64) throws Exception {
		return Base64.decode(base64.getBytes());
	}

	/**
	 * <p>
	 * 二进制数据编码为BASE64字符串
	 * </p>
	 * 
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	public static String encode(byte[] bytes) throws Exception {
		return new String(Base64.encode(bytes));
	}

}

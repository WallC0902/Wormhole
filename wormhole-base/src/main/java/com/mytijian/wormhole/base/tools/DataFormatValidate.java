package com.mytijian.wormhole.base.tools;

/**
 * 数据格式验证
 *
 * Created by wangchangpeng on 2017/5/27.
 */
public class DataFormatValidate {

    /**
     * 验证电话号码格式
     *
     * @param mobile
     * @return
     */
    public static boolean vaildataMobile(String mobile) {
        if (!mobile.trim().matches("^(1[0-9]{2})[0-9]{8}$")) {
            return false;
        }
        return true;
    }

}

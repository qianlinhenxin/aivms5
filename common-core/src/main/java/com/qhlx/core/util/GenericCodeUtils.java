package com.qhlx.core.util;

import java.util.Random;

/**
 * Created by yxn on 2018-04-13.
 */
public class GenericCodeUtils {
    public static String autoGenericCode(Integer code, int num) {

        Integer nums = code % 65535;
        String result = String.format("%0" + num + "d", nums);
        return result;
    }

    /**
     * 生成卡号前缀
     * @return
     */
    public static String GetRandomPre() {
        Random random = new Random();
        int num = random.nextInt(100)+100;
        return PadLeftUtil.padRight("" + num, 3, '0');
    }
}

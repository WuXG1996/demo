package com.example.demo.utils;


import org.apache.commons.lang3.StringUtils;

import java.security.SecureRandom;
import java.util.UUID;

public class IdGenerate {

    private static SecureRandom random = new SecureRandom();

    /**
     * 生成UUID, 中间无-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 使用SecureRandom随机生成Long.
     */
    public static long randomLong() {
        return Math.abs(random.nextLong());
    }

    /**
     * 获取新代码编号
     */
    public static String nextCode(String code){
        if (code != null){
            String str = code.trim();
            int len = str.length() - 1;
            int lastNotNumIndex = 0;
            for (int i = len; i >= 0; i--) {
                if (!(str.charAt(i) >= '0' && str.charAt(i) <= '9')) {
                    lastNotNumIndex = i;
                    break;
                }
            }
            // 如果最后一位是数字，并且last索引位置还在最后，则代表是纯数字，则最后一个不是数字的索引为-1
            if ((str.charAt(len) >= '0' && str.charAt(len) <= '9') && (lastNotNumIndex == len)) {
                lastNotNumIndex = -1;
            }
            String prefix = str.substring(0, lastNotNumIndex + 1);
            String numStr = str.substring(lastNotNumIndex + 1, str.length());
            long num = StringUtils.isBlank(numStr) ? 0l : Long.valueOf(numStr); //ObjectUtils.toLong(numStr);
//			System.out.println("处理前："+str);
            str = prefix + StringUtils.leftPad(String.valueOf(num + 1), numStr.length(), "0");
//			System.out.println("处理后："+str);
            return str;
        }
        return null;
    }

    /**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取一定长度的随机数字
     * @param length
     * @return
     */
    public static String getRandomNumByLength(int length) {
        String base = "0123456789";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}

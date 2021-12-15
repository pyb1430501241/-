package com.pdsu.banmeng.utils;

import java.util.Arrays;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-12-15 19:33
 */
public abstract class EmailUtils {

    /**
     * 对邮箱进行简单加密
     * 例如： 143050241@qq.com ---> 143*****41@qq.com
     * @param email 待加密邮箱
     * @return
     * 加密结果
     */
    public static String encryptionEmail(String email) {
        // @之后
        String suffix = email.substring(email.lastIndexOf("@"));
        // 需加密的部分
        String simple = email.replace(suffix, "");

        char[] chars = simple.toCharArray();

        int index = chars.length / 4 + 1;
        int encryptionLength = chars.length / 2 + 1;

        StringBuilder endEmail = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {

            if(i >= index && i <= encryptionLength) {
                endEmail.append("*");
            } else {
                endEmail.append(chars[i]);
            }

        }

        endEmail.append(suffix);

        return endEmail.toString();
    }

}

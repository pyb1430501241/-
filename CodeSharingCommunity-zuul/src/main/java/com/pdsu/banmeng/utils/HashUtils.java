package com.pdsu.banmeng.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.lang.NonNull;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-22 19:17
 */
public abstract class HashUtils {

    private static final String ALGORITHM_NAME = "MD5";

    /**
     * MD5 加密
     * @param account 盐
     * @param password 加密对象
     */
    @NonNull
    public static String md5(@NonNull String account, @NonNull String password) {
        SimpleHash hash = new SimpleHash(ALGORITHM_NAME, password, account, 2);
        return hash.toString();
    }

}

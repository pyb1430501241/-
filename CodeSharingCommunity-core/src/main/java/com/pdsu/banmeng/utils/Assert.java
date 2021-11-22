package com.pdsu.banmeng.utils;

import com.pdsu.banmeng.enums.StatusEnum;
import com.pdsu.banmeng.exception.BusinessException;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-21 16:25
 */
public abstract class Assert {

    public static void isTrue(boolean b, StatusEnum statusEnum) {
        if(b) {
           return;
        }
        throw new BusinessException(statusEnum);
    }

    public static void isFalse(boolean b, StatusEnum statusEnum) {
        isTrue(!b, statusEnum);
    }

    public static void isNull(Object obj, StatusEnum statusEnum) {
        isTrue(obj == null, statusEnum);
    }

    public static void nonNull(Object obj, StatusEnum statusEnum) {
        isTrue(obj != null, statusEnum);
    }

}

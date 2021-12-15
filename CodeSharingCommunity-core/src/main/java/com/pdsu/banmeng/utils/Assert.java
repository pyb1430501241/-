package com.pdsu.banmeng.utils;

import com.pdsu.banmeng.enums.StatusEnum;
import com.pdsu.banmeng.exception.BusinessException;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-21 16:25
 */
public abstract class Assert {

    /**
     *  如果为 false 则抛出异常
     * @param b 条件
     * @param statusEnum 异常信息
     */
    public static void isTrue(boolean b, StatusEnum statusEnum) {
        if(b) {
           return;
        }
        throw new BusinessException(statusEnum);
    }

    /**
     * 如果为 true 则抛出异常
     * @param b 条件
     * @param statusEnum 异常信息
     */
    public static void isFalse(boolean b, StatusEnum statusEnum) {
        isTrue(!b, statusEnum);
    }

    /**
     * 如果不为 null 则抛出异常
     * @param obj 对象
     * @param statusEnum 异常信息
     */
    public static void isNull(Object obj, StatusEnum statusEnum) {
        isTrue(obj == null, statusEnum);
    }

    /**
     * 如果为 null 则抛出异常
     * @param obj 对象
     * @param statusEnum 异常信息
     */
    public static void nonNull(Object obj, StatusEnum statusEnum) {
        isTrue(obj != null, statusEnum);
    }

}

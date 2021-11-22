package com.pdsu.banmeng.utils;

import com.alibaba.fastjson.JSON;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-21 16:52
 */
public abstract class JsonUtils {

    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T toObject(String json, Class<? extends T> clazz) {
        return JSON.parseObject(json, clazz);
    }

}

package com.pdsu.banmeng.enums;

import lombok.*;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-21 16:22
 */
@AllArgsConstructor
@Getter
public enum StatusEnum {

    OK(200, "成功"),
    ERROR(500, "未定义类型错误"),
    NOT_LOGIN(444, "未登录"),
    NO_PERMISSION(445, "权限不足"),
    NOT_FOUND(404, "未找到");

    private Integer code;

    private String msg;


}

package com.pdsu.banmeng.enums;

import lombok.*;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-21 17:11
 */
@AllArgsConstructor
@Getter
public enum RoleEnum {

    TOURIST(1, "游客"),
    USER(2, "普通用户"),
    ADMIN(3, "管理员"),
    SYSTEM(4, "系统")
    ;
    private Integer id;

    private String name;

}

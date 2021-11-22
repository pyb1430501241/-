package com.pdsu.banmeng.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.Arrays;

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
    @EnumValue
    private Integer id;

    @JsonValue
    private String name;

    /**
     * 根据ID匹配权限
     * @param id id
     * @return
     * 如果没有ID 对应的权限, 则默认为普通用户
     */
    public static RoleEnum matchers(@NonNull Integer id) {
        return Arrays.stream(values()).filter(e -> e.id.equals(id)).findFirst().orElse(USER);
    }

}

package com.pdsu.banmeng.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;
import org.springframework.lang.NonNull;

import java.util.Arrays;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-22 19:20
 */
@AllArgsConstructor
@Getter
public enum AccountStatusEnum {

    NORMAL(1, "正常"),
    FROZEN(2, "冻结"),
    BAN(3, "封禁")
    ;

    @EnumValue
    private Integer id;

    @JsonValue
    private String status;

    /**
     * 根据ID匹配权限
     * @param id id
     * @return
     * 如果没有ID 对应的权限, 则默认为普通用户
     */
    public static AccountStatusEnum matchers(@NonNull Integer id) {
        return Arrays.stream(values()).filter(e -> e.id.equals(id)).findFirst().orElse(NORMAL);
    }

}

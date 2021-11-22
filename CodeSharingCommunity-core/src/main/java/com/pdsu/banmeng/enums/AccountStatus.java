package com.pdsu.banmeng.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-22 19:20
 */
@AllArgsConstructor
@Getter
public enum AccountStatus {

    NORMAL(1, "正常"),
    FROZEN(2, "冻结"),
    BAN(3, "封禁")
    ;

    @EnumValue
    private Integer id;

    @JsonValue
    private String status;

}

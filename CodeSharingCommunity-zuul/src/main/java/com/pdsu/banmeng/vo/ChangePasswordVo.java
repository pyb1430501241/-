package com.pdsu.banmeng.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-12-15 20:40
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ChangePasswordVo {

    private Integer uid;

    private String password;

    private String token;

    private String code;

}

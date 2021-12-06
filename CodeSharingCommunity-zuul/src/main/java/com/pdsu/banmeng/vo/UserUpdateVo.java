package com.pdsu.banmeng.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-12-05 21:02
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserUpdateVo {

    private Integer uid;

    private String username;

}

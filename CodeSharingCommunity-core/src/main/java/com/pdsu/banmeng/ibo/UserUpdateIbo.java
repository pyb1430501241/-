package com.pdsu.banmeng.ibo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-12-05 21:04
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserUpdateIbo {

    private Integer uid;

    private String username;

}

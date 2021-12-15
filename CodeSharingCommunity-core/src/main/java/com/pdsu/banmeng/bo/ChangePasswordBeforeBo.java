package com.pdsu.banmeng.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-12-15 19:47
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ChangePasswordBeforeBo {

    private Integer uid;

    private String username;

    private String email;

    private String token;

}

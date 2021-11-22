package com.pdsu.banmeng.context;

import com.pdsu.banmeng.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-20 20:24
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CurrentUser {

    private Integer id;

    private String username;

    private String password;

    private RoleEnum roleEnum;

}

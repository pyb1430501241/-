package com.pdsu.banmeng.context;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pdsu.banmeng.enums.RoleEnum;
import lombok.*;

import java.io.Serializable;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-20 20:24
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrentUser implements Serializable {

    private Integer id;

    private Integer uid;

    private String password;

    private String username;

    private String imgPath;

    private String email;

    private RoleEnum role;

}

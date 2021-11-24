package com.pdsu.banmeng.ibo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-24 18:20
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApplyAccountIbo {

    private String uid;

    private String password;

    private String username;

    private String email;

}

package com.pdsu.banmeng.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-24 22:44
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EmailBo {

    private Integer uid;

    private String email;

}

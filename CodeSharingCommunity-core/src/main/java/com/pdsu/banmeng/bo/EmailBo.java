package com.pdsu.banmeng.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-24 22:44
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EmailBo implements Serializable {

    private Integer uid;

    private String email;

}

package com.pdsu.banmeng.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-26 21:53
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReversalStatusVo {

    private Integer webId;

    private Integer bid;

    private Integer likeId;

    @NotBlank
    private String type;

}

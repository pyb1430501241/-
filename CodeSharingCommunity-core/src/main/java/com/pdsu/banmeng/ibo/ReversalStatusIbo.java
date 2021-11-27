package com.pdsu.banmeng.ibo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-26 22:04
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReversalStatusIbo {

    private Integer webId;

    private Integer bid;

    private Integer likeId;

    private String type;

}

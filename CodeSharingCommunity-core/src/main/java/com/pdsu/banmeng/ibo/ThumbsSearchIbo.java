package com.pdsu.banmeng.ibo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-25 20:56
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ThumbsSearchIbo {

    private Integer uid;

    private Integer bid;

    private Integer webId;

}

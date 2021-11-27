package com.pdsu.banmeng.ibo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-26 22:18
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ThumbsRemoveIbo {

    private Integer id;

    private Integer uid;

    private Integer webId;

    private Integer bid;

}

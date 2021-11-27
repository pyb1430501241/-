package com.pdsu.banmeng.ibo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-25 21:42
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CollectionSearchIbo {

    /**
     * 被收藏人学号,绑定表db_user_information里的uid
     */
    private Integer bid;

    private Integer wid;

}

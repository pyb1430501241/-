package com.pdsu.banmeng.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-26 22:43
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReversalBo {

    // true 为点赞 or 收藏 or 关注
    // 反之为取消点赞 or 收藏 or 关注
    private Boolean status;

    private String msg;

}

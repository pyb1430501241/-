package com.pdsu.banmeng.vo;

import com.pdsu.banmeng.context.RequestContext;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-27 19:09
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FansSearchVo {

    private Integer uid = RequestContext.currentUser().getUid();

    private Integer p = 1;

    private Integer size = 10;

}

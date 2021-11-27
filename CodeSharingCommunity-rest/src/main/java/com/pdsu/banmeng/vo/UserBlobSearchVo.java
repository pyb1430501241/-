package com.pdsu.banmeng.vo;

import com.pdsu.banmeng.context.RequestContext;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-27 16:47
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserBlobSearchVo {

    private Integer uid = RequestContext.currentUser().getUid();

    private Integer p = 1;

}

package com.pdsu.banmeng.ibo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-27 16:52
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserBlobSearchIbo {

    private Integer uid;

    private Integer p;

    private Integer size;

}

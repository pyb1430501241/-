package com.pdsu.banmeng.ibo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-25 16:33
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BlobSearchIbo {

    private Integer p;

    private Integer size;

    private Integer lid;

    private Integer uid;

    private Integer type;

}

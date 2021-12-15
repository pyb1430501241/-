package com.pdsu.banmeng.ibo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-12-15 16:22
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SimpleBlobUpdateIbo {

    private Integer id;

    private Integer uid;

    private String title;

    private Integer type;

    private String data;

}

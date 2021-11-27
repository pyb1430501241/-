package com.pdsu.banmeng.ibo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-25 11:51
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SimpleBlobInsertIbo {

    private Integer uid;

    private String title;

    private Integer type;

    private String data;

}

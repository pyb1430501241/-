package com.pdsu.banmeng.ibo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-12-15 16:20
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BlobUpdateIbo {

    private Integer id;

    private String title;

    private Integer type;

    private String data;

    private List<Integer> labelIds;

}

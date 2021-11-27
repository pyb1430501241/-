package com.pdsu.banmeng.ibo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-25 11:35
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BlobInsertIbo {

    private String title;

    private Integer type;

    private String data;

    private List<Integer> labelIds;

}

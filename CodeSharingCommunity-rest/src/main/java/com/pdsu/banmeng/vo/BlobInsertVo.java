package com.pdsu.banmeng.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-25 11:21
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BlobInsertVo {

    @NotBlank
    private String title;

    private Integer type;

    @NotBlank
    private String data;

    @Size(max = 5)
    private List<Integer> labelIds;

}

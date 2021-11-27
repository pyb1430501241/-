package com.pdsu.banmeng.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-25 10:58
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WebLabelBo implements Serializable {

    private Integer id;

    private String label;

}

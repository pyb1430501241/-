package com.pdsu.banmeng.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-25 16:38
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SimpleBlobBo implements Serializable {

    private Integer id;

    private Integer uid;

    private String title;

    private Integer type;

    private Date createTime;

}

package com.pdsu.banmeng.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-25 19:52
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BlobInformationBo implements Serializable {

    private Integer id;

    private Integer uid;

    private String title;

    private Integer type;

    private Date createTime;

    private String data;

    private Integer visit;

    private Integer thumbs;

    private Integer collection;

    private Boolean thumbsStatus;

    private Boolean collectionStatus;

    private Boolean editable;

    private List<WebLabelBo> labels;

}

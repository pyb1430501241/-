package com.pdsu.banmeng.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-25 20:27
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorBo implements Serializable {

    private Integer id;

    private Integer uid;

    private String username;

    private String imagePath;

    private Boolean like;

    private Integer fansNumber;

    private Integer thumbsNumber;

    private Integer commentNumber;

    private Integer visitNumber;

    private Integer collectionNumber;

    private Integer originalNumber;

    private Integer attentionNumber;

    private Integer fileNumber;

    private Integer downloadNumber;

    private List<SimpleBlobBo> lastBlob;

}

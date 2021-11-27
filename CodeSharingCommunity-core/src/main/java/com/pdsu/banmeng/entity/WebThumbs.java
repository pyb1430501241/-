package com.pdsu.banmeng.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author 半梦
 * @since 2021-11-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@TableName("db_web_thumbs")
public class WebThumbs implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 点赞人学号,绑定db_user_information的uid
     */
    private Integer uid;

    /**
     * 被点赞的文章作者学号,绑定db_user_information的uid
     */
    private Integer bid;

    /**
     * 被点赞的文章,绑定db_web_information的id
     */
    private Integer webId;

    private Date createTime;

    private Date updateTime;

}

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
@TableName("db_collection")
public class Collection implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 收藏人学号,绑定表db_user_information里的uid
     */
    private Integer uid;

    /**
     * 被收藏网页id,绑定db_web_information里的id
     */
    private Integer wid;

    /**
     * 被收藏人学号,绑定表db_user_information里的uid
     */
    private Integer bid;

    private Date createTime;

    private Date updateTime;


}

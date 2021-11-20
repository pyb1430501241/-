package com.pdsu.banmeng.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 半梦
 * @since 2021-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("db_comment_reply")
public class CommentReply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文章ID
     */
    private Integer wid;

    /**
     * 评论ID
     */
    private Integer cid;

    /**
     * 评论人UID
     */
    private Integer uid;

    /**
     * 回复内容
     */
    private String content;

    /**
     * 被评论人UID
     */
    private Integer bid;

    /**
     * 点赞数
     */
    private Integer thumb;

    /**
     * 回复时间
     */
    private Date createTime;

    private Date updateTime;

    @TableLogic
    private Boolean deleted;


}

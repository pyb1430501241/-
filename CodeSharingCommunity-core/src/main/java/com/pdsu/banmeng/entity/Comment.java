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
@TableName("db_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文章ID，绑定db_web_information表的ID
     */
    private Integer wid;

    /**
     * 评论者ID，绑定db_user_information表的UID
     */
    private Integer uid;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 点赞数
     */
    private Integer thumb;

    /**
     * 评论时间
     */
    private Date createTime;

    private Date updateTime;

    /**
     * 状态
     */
    private Integer commentState;

    @TableLogic
    private Boolean deleted;

}

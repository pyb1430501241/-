package com.pdsu.banmeng.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.sql.Blob;
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
@TableName("db_web_information")
public class WebInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章主体内容
     */
    private Blob webData;

    /**
     * 投稿人学号，绑定db_userinformation里的uid
     */
    private Integer uid;

    /**
     * 投稿类型,绑定表db_contype的id
     */
    private Integer type;

    /**
     * 投稿时间

     */
    private Date createTime;

    private Date updateTime;

    @TableLogic
    private Boolean deleted;

    @Version
    private Integer version;


}

package com.pdsu.banmeng.entity;

import com.baomidou.mybatisplus.annotation.*;

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
@TableName("db_web_file")
public class WebFile implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 上传人学号,绑定db_user_information里的uid
     */
    private Integer uid;

    /**
     * 文件标题
     */
    private String title;

    /**
     * 文件简介
     */
    private String description;

    /**
     * 下载文件储存地址
     */
    private String filePath;

    /**
     * 上传时间
     */
    private Date createTime;

    private Date updateTime;

    @TableLogic
    private Boolean deleted;

    @Version
    private Integer version;


}

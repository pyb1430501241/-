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
@TableName("db_email")
public class Email implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学号，绑定db_user_information
     */
    private Integer uid;

    /**
     * 邮箱
     */
    private String email;

    private Date createTime;

    private Date updateTime;

    @TableLogic
    private Boolean deleted;

    @Version
    private Integer version;


}

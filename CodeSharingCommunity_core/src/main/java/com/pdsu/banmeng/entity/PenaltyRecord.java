package com.pdsu.banmeng.entity;

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
@TableName("db_penalty_record")
public class PenaltyRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 被处罚人学号,绑定db_user_information里的uid
     */
    private Integer uid;

    /**
     * 处罚理由
     */
    private String reasons;

    /**
     * 处罚时长
     */
    private String dutation;

    /**
     * 处罚时间
     */
    private Date createTime;

    private Date updateTime;


}

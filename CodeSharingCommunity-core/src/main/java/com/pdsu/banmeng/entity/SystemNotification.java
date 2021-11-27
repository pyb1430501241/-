package com.pdsu.banmeng.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@TableName("db_system_notification")
public class SystemNotification implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 被通知人学号
     */
    private Integer uid;

    /**
     * 通知内容
     */
    private String content;

    /**
     * 通知人学号
     */
    private Integer sid;

    /**
     * 1为未读，2为已读
     */
    private Integer unread;

    /**
     * 通知时间
     */
    private Date createTime;

    private Date updateTime;


}

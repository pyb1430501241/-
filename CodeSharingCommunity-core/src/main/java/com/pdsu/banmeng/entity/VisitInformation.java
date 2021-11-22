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
@TableName("db_visit_information")
public class VisitInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 访问人学号，绑定db_user_infromation里的uid
     */
    private Integer vid;

    /**
     * 被访问人学号,绑定db_user_infromation里的uid
     */
    private Integer sid;

    /**
     * 被访问网页id，绑定db_web_information里的id
     */
    private Integer wid;

    private Date createTime;

    private Date updateTime;

    @TableLogic
    private Boolean deleted;


}

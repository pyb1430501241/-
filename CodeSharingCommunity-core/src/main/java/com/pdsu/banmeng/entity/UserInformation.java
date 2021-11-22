package com.pdsu.banmeng.entity;

import com.baomidou.mybatisplus.annotation.*;

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
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName("db_user_information")
public class UserInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学号/工号
     */
    private Integer uid;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 用户名
     */
    private String username;

    /**
     * 账号状态
     */
    private AccountStatus accountStatus;

    /**
     * 申请时间
     */
    private Date createTime;

    private Date updateTime;

    @TableLogic
    private Boolean deleted;

    @Version
    private Integer version;


}

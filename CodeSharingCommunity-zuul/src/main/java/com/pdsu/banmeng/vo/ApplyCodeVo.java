package com.pdsu.banmeng.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-23 14:43
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApplyCodeVo {

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不可为空")
    private String name;

    @Email(message = "邮箱格式不正确")
    @ApiModelProperty("邮箱")
    private String email;

}

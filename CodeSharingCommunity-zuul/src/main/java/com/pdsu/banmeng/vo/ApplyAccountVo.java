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
 * @since 2021-11-23 15:40
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApplyAccountVo {

    @ApiModelProperty("账号")
    @NotBlank
    private String uid;

    @ApiModelProperty("密码")
    @NotBlank
    private String password;

    @ApiModelProperty("昵称")
    @NotBlank
    private String username;

    @ApiModelProperty("邮箱")
    @Email
    private String email;

    @ApiModelProperty("验证码key")
    @NotBlank
    private String key;

    @ApiModelProperty("验证码")
    @NotBlank
    private String code;

}

package com.pdsu.banmeng.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-22 18:57
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LoginVo {

    @ApiModelProperty("账号")
    private String uid;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不允许为空")
    private String password;

    @ApiModelProperty("记住我, 1 -> 记住, 反之不记住")
    private Integer rememberMe;

}

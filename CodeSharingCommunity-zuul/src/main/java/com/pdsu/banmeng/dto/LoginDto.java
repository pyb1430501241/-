package com.pdsu.banmeng.dto;

import com.pdsu.banmeng.context.CurrentUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-24 20:49
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LoginDto {

    private CurrentUser currentUser;

    private Serializable token;

}

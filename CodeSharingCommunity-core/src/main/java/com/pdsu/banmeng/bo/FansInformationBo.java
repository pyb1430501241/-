package com.pdsu.banmeng.bo;

import com.pdsu.banmeng.context.CurrentUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-27 19:13
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FansInformationBo implements Serializable {

    private CurrentUser user;

    private Boolean like = false;

}

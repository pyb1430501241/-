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
 * @since 2021-11-25 16:32
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SimpleBlobIndexBo extends SimpleUserBlobBo implements Serializable {

   private CurrentUser user;

}

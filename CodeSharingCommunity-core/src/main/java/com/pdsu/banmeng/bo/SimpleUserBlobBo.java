package com.pdsu.banmeng.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-27 16:52
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SimpleUserBlobBo implements Serializable {

    protected SimpleBlobBo blob;

    protected Integer visit;

    protected Integer thumbs;

    protected Integer collection;

}

package com.pdsu.banmeng.ibo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-12-05 20:30
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ImageUpdateIbo {

    /**
     * 学号,绑定db_user_information的uid
     */
    private Integer uid;

    /**
     * 照片路径
     */
    private String imagePath;

}

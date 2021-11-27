package com.pdsu.banmeng.ibo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-25 21:03
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VisitSearchIbo {

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


}

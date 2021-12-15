package com.pdsu.banmeng.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-12-07 15:23
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DownloadBo {

    private Resource resource;

    private String fileName;

}

package com.pdsu.banmeng.ibo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-12-06 19:14
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FileInsertIbo {

    private MultipartFile file;

    private Integer uid;

    private String title;

    private String description;

    private String filePath;

}

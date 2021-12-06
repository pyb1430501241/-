package com.pdsu.banmeng.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-12-06 19:04
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FileInsertVo {

    @NotNull
    private MultipartFile file;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

}

package com.pdsu.banmeng.enums;

import lombok.*;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-12-05 18:30
 */
@AllArgsConstructor
@Getter
public enum FileTypeEnum {

    USER_IMAGE(Paths.get("/codeSharingCommunity/user/image")),
    BLOB_IMAGE(Paths.get("/codeSharingCommunity/blob/image")),
    FILE(Paths.get("/codeSharingCommunity/file/"))
    ;

    private Path path;

}

package com.pdsu.banmeng.utils;

import com.pdsu.banmeng.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-12-05 18:01
 */
public abstract class FileUtils {

    /**
     * 获取新的文件名
     * uuid
     * @param oldName
     * @return
     */
    @NonNull
    public static String newFileName(@Nullable String oldName) {
        Assert.nonNull(oldName, StatusEnum.FILE_INIT_ERROR);

        return RandomUtils.getUUID() + getSuffix(oldName);
    }

    @NonNull
    @SuppressWarnings("all")
    public static String getSuffix(@Nullable String name) {
        Assert.nonNull(name, StatusEnum.FILE_INIT_ERROR);

        return name.substring(name.lastIndexOf("."));
    }

}

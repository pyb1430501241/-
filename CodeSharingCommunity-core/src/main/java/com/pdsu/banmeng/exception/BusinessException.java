package com.pdsu.banmeng.exception;

import com.pdsu.banmeng.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-21 16:18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

    private StatusEnum statusEnum;

    public BusinessException(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

}

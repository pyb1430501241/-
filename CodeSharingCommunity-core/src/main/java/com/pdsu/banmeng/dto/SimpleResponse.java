package com.pdsu.banmeng.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pdsu.banmeng.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-21 16:32
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleResponse<T> {

    private Integer code;

    private String msg;

    private T data;

    public SimpleResponse(T data) {
        this.code = StatusEnum.OK.getCode();
        this.msg = StatusEnum.OK.getMsg();
        this.data = data;
    }

    public SimpleResponse(StatusEnum statusEnum) {
        this.code = statusEnum.getCode();
        this.msg = statusEnum.getMsg();
    }

    public SimpleResponse(T data, StatusEnum statusEnum) {
        this.data = data;
        this.code = statusEnum.getCode();
        this.msg = statusEnum.getMsg();
    }

}

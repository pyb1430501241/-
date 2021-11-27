package com.pdsu.banmeng.bo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-25 16:28
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PageTemplateBo<T> implements Serializable {

    /**
     * 数据
     */
    private List<T> records;

    /**
     * 是否有下一页
     */
    private Boolean hasNext;

    /**
     * 是否有上一页
     */
    private Boolean hasPrevious;

    /**
     * 总数
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long total;

    /**
     * 当前页
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long current;

    /**
     * 总页码
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long pages;

    /**
     * 对数据进行设置
     * @param <E> page 封装的类型
     * @param page mybatis-plus page
     */
    public <E> void init(Page<E> page) {
        this.setPages(page.getPages());
        this.setHasNext(page.hasNext());
        this.setHasPrevious(page.hasPrevious());
        this.current = page.getCurrent();
        this.total = page.getTotal();
    }


}

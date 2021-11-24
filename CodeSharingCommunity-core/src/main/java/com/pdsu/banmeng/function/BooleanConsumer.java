package com.pdsu.banmeng.function;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-24 18:37
 */
@FunctionalInterface
public interface BooleanConsumer<T> {

    boolean accept(T t);

}

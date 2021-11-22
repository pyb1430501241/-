package com.pdsu.banmeng.business;

import com.pdsu.banmeng.enums.RoleEnum;

import java.lang.annotation.*;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-21 17:14
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Authentication {

    RoleEnum [] value() default {};

}

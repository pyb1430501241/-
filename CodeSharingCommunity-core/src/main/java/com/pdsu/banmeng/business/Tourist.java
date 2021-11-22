package com.pdsu.banmeng.business;

import com.pdsu.banmeng.enums.RoleEnum;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-21 17:18
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Authentication
public @interface Tourist {

    @AliasFor(annotation = Authentication.class)
    RoleEnum [] value() default {RoleEnum.TOURIST, RoleEnum.USER, RoleEnum.ADMIN, RoleEnum.SYSTEM};

}

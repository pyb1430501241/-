package com.pdsu.banmeng.business.aspect;

import com.pdsu.banmeng.business.Authentication;
import com.pdsu.banmeng.context.CurrentUser;
import com.pdsu.banmeng.enums.RoleEnum;
import com.pdsu.banmeng.enums.StatusEnum;
import com.pdsu.banmeng.exception.BusinessException;
import com.pdsu.banmeng.utils.Assert;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-21 17:22
 */
public abstract class AbstractAuthenticationAspect {

    protected void invoke(JoinPoint point) {
        // 只支持方法级
        if(!(point.getSignature() instanceof MethodSignature)) {
            return;
        }

        MethodSignature methodSignature = (MethodSignature) point.getSignature();

        AnnotationAttributes mergedAnnotationAttributes = AnnotatedElementUtils.findMergedAnnotationAttributes(methodSignature.getMethod(), Authentication.class, false, true);

        // 如果没有添加鉴权注解, 则认为无需鉴权
        if(Objects.isNull(mergedAnnotationAttributes)) {
            return;
        }

        List<RoleEnum> roles = Arrays.asList(((RoleEnum[]) mergedAnnotationAttributes.get("value")));

        // 游客级方法直接放行
        if(roles.contains(RoleEnum.TOURIST)) {
            return;
        }

        CurrentUser currentUser = currentUser();

        // 结合游客级方法已放行, 如果用户未登录, 则直接认为无权限
        Assert.nonNull(currentUser, StatusEnum.NOT_LOGIN);

        // 如果该方法允许此用户所拥有的角色访问，则放行
        if(roles.contains(currentUser.getRole())) {
            return;
        }

        throw new BusinessException(StatusEnum.NO_PERMISSION);
    }

    public abstract CurrentUser currentUser();

}

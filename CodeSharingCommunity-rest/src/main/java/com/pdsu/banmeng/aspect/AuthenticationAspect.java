package com.pdsu.banmeng.aspect;

import com.pdsu.banmeng.business.Authentication;
import com.pdsu.banmeng.context.CurrentUser;
import com.pdsu.banmeng.context.RequestContext;
import com.pdsu.banmeng.enums.RoleEnum;
import com.pdsu.banmeng.enums.StatusEnum;
import com.pdsu.banmeng.exception.BusinessException;
import com.pdsu.banmeng.utils.Assert;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-21 17:22
 */
@Component
@Order
@Aspect
public class AuthenticationAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void methodPoint() {
    }

    @Pointcut("@annotation(com.pdsu.banmeng.business.Authentication) || @annotation(com.pdsu.banmeng.business.Tourist) || @annotation(com.pdsu.banmeng.business.Admin) || @annotation(com.pdsu.banmeng.business.User) || @annotation(com.pdsu.banmeng.business.System)")
    public void authenticationPoint() {
    }

    @Before(value = "methodPoint() && authenticationPoint()")
    public void before(JoinPoint point) {
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

        CurrentUser currentUser = RequestContext.currentUser();

        // 结合游客级方法已放行, 如果用户未登录, 则直接认为无权限
        Assert.isNull(currentUser, StatusEnum.NOT_LOGIN);

        // 如果该方法允许此用户所拥有的角色访问，则放行
        if(roles.contains(currentUser.getRoleEnum())) {
            return;
        }

        throw new BusinessException(StatusEnum.NO_PERMISSION);
    }

}

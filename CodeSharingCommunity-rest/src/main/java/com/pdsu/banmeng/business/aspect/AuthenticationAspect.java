package com.pdsu.banmeng.business.aspect;


import com.pdsu.banmeng.context.CurrentUser;
import com.pdsu.banmeng.context.RequestContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-22 18:50
 */
@Aspect
@Order
@Component
public class AuthenticationAspect extends AbstractAuthenticationAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void methodPoint() {
    }

    @Pointcut("@annotation(com.pdsu.banmeng.business.Authentication) || @annotation(com.pdsu.banmeng.business.Tourist) || @annotation(com.pdsu.banmeng.business.Admin) || @annotation(com.pdsu.banmeng.business.User) || @annotation(com.pdsu.banmeng.business.System)")
    public void authenticationPoint() {
    }

    @Before(value = "methodPoint() && authenticationPoint()")
    public void before(JoinPoint joinPoint) {
        invoke(joinPoint);
    }

    @Override
    public CurrentUser currentUser() {
        return RequestContext.currentUser();
    }


}

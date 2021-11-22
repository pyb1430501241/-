package com.pdsu.banmeng.interceptor;

import com.pdsu.banmeng.context.CurrentUser;
import com.pdsu.banmeng.context.RequestContext;
import com.pdsu.banmeng.utils.HttpUtils;
import com.pdsu.banmeng.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-21 17:02
 */
@Component
public class UserInterceptor extends HandlerInterceptorAdapter {

    @Value("${debug}")
    private Boolean isDebug;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (isDebug) {
            RequestContext.setCurrentUser(getDevTestUser());
            return super.preHandle(request, response, handler);
        }

        String currentUserJson = request.getHeader(HttpUtils.getSessionHeader());

        if(currentUserJson == null) {
            super.preHandle(request, response, handler);
        }

        RequestContext.setCurrentUser(JsonUtils.toObject(currentUserJson, CurrentUser.class));

        return super.preHandle(request, response, handler);
    }

    private CurrentUser getDevTestUser() {
        return new CurrentUser();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestContext.clear();
        super.afterCompletion(request, response, handler, ex);
    }

}

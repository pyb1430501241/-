package com.pdsu.banmeng.utils;

import com.pdsu.banmeng.context.CurrentUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  shiro 框架工具类
 * @author 半梦
 *
 */
@SuppressWarnings("all")
public abstract class ShiroUtils {

    @Nullable
	public static CurrentUser getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        //取出身份信息
        CurrentUser currentUser = (CurrentUser) subject.getPrincipal();

        if(Objects.nonNull(currentUser)) {
            currentUser.setPassword(null);
        }

        return currentUser;
	}

    /**
     * 获取 sessionId
     */
    @Nullable
    public static String getSessionId() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(false);
        if(Objects.isNull(session)) {
            return null;
        }
        return (String) session.getId();
    }

    /**
     * 获取用户是否已登录
     */
    public static boolean isAuthorization(@NonNull HttpServletRequest request) {
        return isAuthenticated(request) || isRemembered(request);
    }

    /**
     * 用户是否通过登录认证
     */
    public static boolean isAuthenticated(@NonNull HttpServletRequest request) {
        return StringUtils.hasText(getSessionId()) || StringUtils.hasText(HttpUtils.getSessionId(request));
    }

    /**
     * 用户是否通过记住我认证
     */
    public static boolean isRemembered(@NonNull HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null) {
            return false;
        }
        return Stream.of(cookies).map(Cookie :: getName)
                .collect(Collectors.toList()).contains(HttpUtils.getRememberCookieName());
    }

	 /**
	  * 根据 sessionid 获取用户信息
     * @param sessionID
     * @param request
     * @param response
     * @return
     */
    @Nullable
    public static CurrentUser getCurrentUser(@Nullable String sessionID, HttpServletRequest request, HttpServletResponse response) {
        try {
            SessionKey key = new WebSessionKey(sessionID, request, response);
            Session se = SecurityUtils.getSecurityManager().getSession(key);
            Object obj = se.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            SimplePrincipalCollection coll = (SimplePrincipalCollection) obj;
            return (CurrentUser) coll.getPrimaryPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

}

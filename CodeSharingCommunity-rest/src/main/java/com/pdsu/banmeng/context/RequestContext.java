package com.pdsu.banmeng.context;


/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-21 16:59
 */
public class RequestContext {

    private static ThreadLocal<CurrentUser> currentUserLocal = new ThreadLocal<>();

    public static void setCurrentUser(CurrentUser currentUser) {
        currentUserLocal.set(currentUser);
    }

    public static CurrentUser currentUser() {
        return currentUserLocal.get();
    }

    public static void clear() {
        currentUserLocal.remove();
    }

}

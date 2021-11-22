package com.pdsu.banmeng.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.pdsu.banmeng.context.CurrentUser;
import com.pdsu.banmeng.utils.HttpUtils;
import com.pdsu.banmeng.utils.JsonUtils;
import com.pdsu.banmeng.utils.ShiroUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-21 16:46
 */
@Component
public class ZuulPreFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        CurrentUser currentUser = ShiroUtils.getCurrentUser();

        // 如果没有登录, 则直接转发
        if(Objects.isNull(currentUser)) {
            return null;
        }

        RequestContext context = RequestContext.getCurrentContext();
        context.addZuulRequestHeader(HttpUtils.getSessionHeader(), JsonUtils.toJson(currentUser));
        context.setSendZuulResponse(true);

        return null;
    }

}

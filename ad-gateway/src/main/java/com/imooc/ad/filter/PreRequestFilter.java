package com.imooc.ad.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * @author spin
 * @date 2021/4/1516:50
 * @description: TODO 过滤器 用于日志操作
 */
@Slf4j
@Component
public class PreRequestFilter extends ZuulFilter {

    //控制过滤器执行类型
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    //是否需要执行
    @Override
    public boolean shouldFilter() {
        return true;
    }

     /**
     　　* @description: TODO 过滤器的具体操作
     　　* @author yusp
     　　* @date 2021/4/15 17:02
     　　*/
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.set("startTime",System.currentTimeMillis());
        return null;
    }
}

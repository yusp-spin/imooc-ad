package com.imooc.ad.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * @author spin
 * @date 2021/4/1517:10
 * @description: TODO
 */
public class AccessLogFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;//说明是最后调用
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

     /**
     　　* @description: TODO 日志记录操作的时间和路径
     　　* @author yusp
     　　* @date 2021/4/15 17:12
     　　*/
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Long startTime = (Long)ctx.get("startTime");
        //请求路径
        String uri = request.getRequestURI();
        long duration = System.currentTimeMillis() - startTime;

        Logger log = null;
        assert false;
        log.info("uri: " + uri + ", duration: " + duration / 100 + "ms");
        return null;
    }
}

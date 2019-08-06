package com.microservice.filter;

import com.microservice.domain.City;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;

/**
 * @author songabao
 * @date 2019/8/2 18:06
 */
public class MyFilter extends ZuulFilter {
    private static final Logger LOGGER= LoggerFactory.getLogger(MyFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        LOGGER.info("request method:"+request.getMethod());
        LOGGER.info("rquest url:"+request.getRequestURI());
        LOGGER.info("rquest query:"+request.getQueryString());
        String queryString = request.getQueryString();
        if (!queryString.equals("id=1")){
            City city = new City();
            city.setId((long)2);
            city.setStatus(1);
            city.setName("测试zuul的过滤器");
            LOGGER.info(city.toString());
            try {
                currentContext.setSendZuulResponse(false);
                currentContext.setResponseBody(city.toString());
                currentContext.setResponseStatusCode(404);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        return null;
    }
}

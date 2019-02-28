package com.peaceful.apmlite;

import com.google.common.collect.Maps;

import org.apache.commons.lang.StringUtils;
import org.perf4j.StopWatch;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangjun on 2019-02-03.
 */
public class MyServletFilter implements Filter {

    private StopWatch stopWatch = new MyStopWatch();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        response.setCharacterEncoding("UTF-8");
        String url = servletRequest.getRequestURI();
        try {
            if (StringUtils.isNotBlank(url)) {
                stopWatch.start();
                chain.doFilter(request, response);
                stopWatch.stop(url);
            } else {
                chain.doFilter(request, response);
            }
        } catch (IllegalArgumentException | APMException e) {
            Map<String, Object> data = Maps.newHashMap();
            data.put("message",e.getMessage());
            Response.Template(response, "/static/message.vm", data);
        }
    }

    @Override
    public void destroy() {

    }
}

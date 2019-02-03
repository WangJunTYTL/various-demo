package com.peaceful.apmlite;

import org.apache.commons.lang.StringUtils;
import org.perf4j.StopWatch;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangjun on 2019-02-03.
 */
public class MyRequestFilter implements Filter {

    private StopWatch stopWatch = new MyStopWatch();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        String url = servletRequest.getRequestURI();
        if (StringUtils.isNotBlank(url)){
            stopWatch.start();
            chain.doFilter(request,response);
            stopWatch.stop();
        }else{
            chain.doFilter(request,response);
        }



    }

    @Override
    public void destroy() {

    }
}

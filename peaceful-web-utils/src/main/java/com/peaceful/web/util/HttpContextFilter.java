package com.peaceful.web.util;

import javax.servlet.*;
import java.io.IOException;

/**
 * Date 14/10/21.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public class HttpContextFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Http.setRequest((javax.servlet.http.HttpServletRequest) request);
        Http.setResponse((javax.servlet.http.HttpServletResponse) response);
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}

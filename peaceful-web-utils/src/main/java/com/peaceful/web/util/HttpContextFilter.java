package com.peaceful.web.util;

import com.peaceful.util.StringUtils;
import com.peaceful.util.Util;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
        Cookie[] cookies = ((HttpServletRequest) request).getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String key = cookie.getName();
                String session_key;
                if (StringUtils.isNotEmpty(key) && key.indexOf(Http.SESSION_COOKIE) != -1) {
                    session_key = key.substring(Http.SESSION_COOKIE.length());
                    Http.session(session_key, Http.session(session_key));
                }
            }
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}

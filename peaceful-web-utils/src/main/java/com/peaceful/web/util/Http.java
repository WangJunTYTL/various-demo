package com.peaceful.web.util;

import com.peaceful.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Date 14/10/21.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public class Http {

    /**
     * 获得HttpServletRequest
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        return HttpContext.requestThreadLocal.get();
    }

    static void setRequest(HttpServletRequest request) {
        HttpContext.requestThreadLocal.set(request);
    }

    /**
     * 获得HttpServletResponse
     *
     * @return
     */
    public static HttpServletResponse getResponse() {
        return HttpContext.responseThreadLocal.get();
    }

    static void setResponse(HttpServletResponse response) {
        HttpContext.responseThreadLocal.set(response);
    }


    public static String getCookie(String key, String path) {
        if (key == null)
            return null;
        Cookie[] cookies = getRequest().getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (path == null || path.equals("/")) {
                    if (key.equals(cookie.getName()) && (StringUtils.isEmpty(cookie.getPath()) || cookie.getPath().equals("/"))) {
                        return cookie.getValue();
                    }
                } else {
                    if (key.equals(cookie.getName()) && cookie.getPath() != null && cookie.getPath().equals(path)) {
                        String value = cookie.getValue();
                        if (StringUtils.isNotEmpty(value))
                            return value;
                    }
                }

            }
        }
        return null;
    }

    /**
     * 在当前路径下放入cookie
     *
     * @param key
     * @param value
     */
    public static void addCookie(String key, String value) {
        addCookie(key, value, null, -1, false);
    }

    /**
     * 在指定路径下放入cookie
     *
     * @param key
     * @param value
     * @param path
     */
    public static void addCookie(String key, String value, String path) {
        addCookie(key, value, path, -1, false);
    }

    /**
     * 可有选择性的加入cookie
     *
     * @param key
     * @param value
     * @param path
     * @param expire
     */
    public static void addCookie(String key, String value, String path, int expire, boolean httpOnly) {
        Cookie[] cookies = getRequest().getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (key.equals(cookie.getName()) && cookie.getPath() != null && cookie.getPath().equals(getRequest().getRequestURI())) {
                    cookie.setValue(value);
                    if (path != null)
                        cookie.setPath(path);
                    cookie.setHttpOnly(httpOnly);
                    cookie.setMaxAge(expire);
                    getResponse().addCookie(cookie);
                    return;
                }
            }
        }
        javax.servlet.http.Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(expire);
        cookie.setHttpOnly(httpOnly);
        if (path != null)
            cookie.setPath(path);
        getResponse().addCookie(cookie);
    }

    /**
     * 删除当前路径为key的cookie
     *
     * @param key
     */
    public static void deleteCookie(String key) {
        Cookie[] cookies = getRequest().getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (key.equals(cookie.getName()) && cookie.getPath() != null && cookie.getPath().equals(getRequest().getRequestURI())) {
                    cookie.setMaxAge(0);
                    getResponse().addCookie(cookie);
                }
            }
        }
    }


    private static class HttpContext {
        static ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal<HttpServletRequest>();
        static ThreadLocal<HttpServletResponse> responseThreadLocal = new ThreadLocal<HttpServletResponse>();

    }
}




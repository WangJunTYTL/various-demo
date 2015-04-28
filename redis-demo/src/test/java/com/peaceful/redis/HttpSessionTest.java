package com.peaceful.redis;

import com.peaceful.common.util.Util;
import org.junit.*;
import org.junit.Test;


public class HttpSessionTest {

    HttpSession httpSession;

    @Before
    public void setUp() throws Exception {
        httpSession = HttpSessionImpl.getInstance("123456", 300);
    }

    @org.junit.Test
    public void testGet() throws Exception {
       Util.report(httpSession.get("user", String.class));
    }

    @Test
    public void testPut() throws Exception {
        httpSession.put("user", "wj");
    }

    @Test
    public void testClear() throws Exception {
        httpSession.clear("user");
    }

    @Test
    public void testClearAll() throws Exception {
        httpSession.clearAll();
    }
}
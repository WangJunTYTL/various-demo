package com.peaceful.demo.jetty;

import com.alibaba.fastjson.JSON;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 嵌入jetty：http://www.eclipse.org/jetty/documentation/current/embedding-jetty.html
 *
 * Created by wangjun on 2016/11/10.
 */
public class T1 {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8081);

        ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS);

        context.setContextPath("/");
        context.setResourceBase(System.getProperty("java.io.tmpdir"));
        server.setHandler(context);
        // Add myself servlet
        context.addServlet(MyServlet.class, "/dump/*");
        // Add default servlet
        context.addServlet(DefaultServlet.class, "/");
        server.start();
        server.join();
    }

    static class MyServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setStatus(200);
            resp.getWriter().print("HelloImpl World");
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String cmd = req.getParameter("invoke_info");
            System.out.print(cmd);
            Request request = JSON.parseObject(cmd, Request.class);
            Response response = new Response();
            response.netCost = System.currentTimeMillis() - request.requestTime;
            long start = System.currentTimeMillis();
            try {
                Method method = request.zlass.getMethod(request.method, request.paramsType);
                response.data = JSON.toJSONString(method.invoke(HelloImpl.class.newInstance(), request.args));
            } catch (Exception e) {
                response.isHasException = true;
                response.e = e;
                e.printStackTrace();
            }
            response.invokeCost = System.currentTimeMillis() - start;
            resp.getWriter().print(JSON.toJSONString(response));
        }
    }

}

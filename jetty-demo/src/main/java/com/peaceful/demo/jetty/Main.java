package com.peaceful.demo.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.resource.Resource;

/**
 * 嵌入jetty：http://www.eclipse.org/jetty/documentation/current/embedding-jetty.html
 *
 * Created by wangjun on 2016/11/10.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // 设置服务端口及IO处理connector
        Server server = new Server(8787);

        // 实现ServletContext接口
        ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS);

        context.setContextPath("/");
        // 设置静态资源，采用classpath路径,也可以通过绝对路径指明静态资源位置
        context.setBaseResource(Resource.newClassPathResource("/static"));
        // 设置静态资源，采用相对路径方式
        //context.setResourceBase(System.getProperty("java.io.tmpdir"));
        server.setHandler(context);
        // Add myself servlet  127.0.0.1:8787/dump
        context.addServlet(MyServlet.class, "/dump");
        // Add default servlet  该Servlet是默认的，可以在找不到Servlet的时候，使用该Servlet，比如对静态资源的处理
        context.addServlet(DefaultServlet.class, "/");
        server.start();
        server.join();
    }

}

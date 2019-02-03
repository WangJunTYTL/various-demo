package com.peaceful.apmlite;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

/**
 * 嵌入jetty：http://www.eclipse.org/jetty/documentation/current/embedding-jetty.html
 *
 * Created by wangjun on 2016/11/10.
 */
public class MyWebServer {

    public static void setUp(MyRDB myRDB) {
        Server server = new Server(8787);

        ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS);

        context.setContextPath("/");
        // 这种方式设置的项目路劲
        //context.setResourceBase("src/main/resources/static/");
        // 通过classpath寻找数据
        context.setBaseResource(Resource.newClassPathResource("/static"));
        server.setHandler(context);
        context.addFilter(MyRequestFilter.class,"/*", EnumSet.of(DispatcherType.REQUEST));
        // Add myself servlet  127.0.0.1:8787/dump
        ServletHolder servletHolder = new ServletHolder(new MyServlet(myRDB));
        context.addServlet(servletHolder, "/dump");
        // Add default servlet
        context.addServlet(DefaultServlet.class, "/");
        try {
            server.start();
//            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

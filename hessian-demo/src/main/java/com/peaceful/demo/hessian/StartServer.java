package com.peaceful.demo.hessian;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 * 嵌入jetty：http://www.eclipse.org/jetty/documentation/current/embedding-jetty.html
 *
 * Created by wangjun on 2016/11/10.
 */
public class StartServer {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8787);

        ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS);

        context.setContextPath("/");
        context.setResourceBase(System.getProperty("java.io.tmpdir"));
        server.setHandler(context);
        // Add myself servlet  127.0.0.1:8787/dump
        context.addServlet(HelloServicePublish.class, "/hessian");
        // Add default servlet
        context.addServlet(DefaultServlet.class, "/");
        server.start();
        server.join();
    }

}

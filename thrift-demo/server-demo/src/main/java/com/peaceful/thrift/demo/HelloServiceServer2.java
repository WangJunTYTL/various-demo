package com.peaceful.thrift.demo;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import service.demo.HelloServer;

/**
 * Created by Jun on 2018/11/29.
 */
public class HelloServiceServer2 {


    public static void main(String[] args) throws Exception {
        Server server = new Server(8787);

        ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS);

        context.setContextPath("/");
        context.setResourceBase(System.getProperty("java.io.tmpdir"));
        server.setHandler(context);
        // Add myself servlet  127.0.0.1:8787/dump
        TProcessor tProcessor = new HelloServer.Processor(new HelloServiceImpl());
        TServlet tServlet = new TServlet(tProcessor, new TBinaryProtocol.Factory());
//        context.addServlet(tServlet,"/TServer");
        ServletHolder servletHolder = new ServletHolder(tServlet);
        context.addServlet(servletHolder, "/TServer");
        context.addServlet(MyServlet.class, "/test");
        // Add default servlet
        context.addServlet(DefaultServlet.class, "/");
        server.start();
        server.join();
    }
}

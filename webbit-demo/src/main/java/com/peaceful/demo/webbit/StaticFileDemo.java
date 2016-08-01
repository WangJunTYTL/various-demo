package com.peaceful.demo.webbit;

import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import org.webbitserver.handler.StaticFileHandler;

import java.util.concurrent.ExecutionException;

/**
 * @author WangJun
 * @version 1.0 16/7/12
 */
public class StaticFileDemo {
    private final static String ROOT_DIR = System.getProperty("user.dir");
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        WebServer webServer = WebServers.createWebServer(8080)
                .add(new StaticFileHandler(ROOT_DIR)) // path to web content
                .start()
                .get();
    }
}
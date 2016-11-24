package com.peaceful.apm.dashboard;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.JettyWebXmlConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;

/**
 * Created by JunWang on 2016/11/22.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        //Create the server
        Server server = new Server(8080);

        //Enable parsing of jndi-related parts of web.xml and jetty-env.xml
        org.eclipse.jetty.webapp.Configuration.ClassList classlist = org.eclipse.jetty.webapp.Configuration.ClassList.setServerDefault(server);
        classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration", "org.eclipse.jetty.plus.webapp.EnvConfiguration", "org.eclipse.jetty.plus.webapp.PlusConfiguration");
        classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration", "org.eclipse.jetty.annotations.AnnotationConfiguration");

        //Create a WebApp
        WebAppContext webapp = new WebAppContext();
        String webFolder = "webapp";

        webapp.setResourceBase("src/main/resources/" + webFolder);
        Configuration.ClassList clist = Configuration.ClassList.setServerDefault(server);
        clist.addBefore(JettyWebXmlConfiguration.class.getName(), AnnotationConfiguration.class.getName());
        webapp.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*(/target/classes/|.*.jar)");
        webapp.setParentLoaderPriority(true);
        webapp.setInitParameter("useFileMappedBuffer", "false");


        ResourceHandler staticResourceHandler = new ResourceHandler();
        staticResourceHandler.setDirectoriesListed(false);
        Resource staticResources = Resource.newClassPathResource(webFolder);
        staticResourceHandler.setBaseResource(staticResources);
        staticResourceHandler.setWelcomeFiles(new String[]{"html/index.html"});

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{staticResourceHandler, webapp});

        server.setHandler(handlers);


        server.start();
        server.join();
    }
}

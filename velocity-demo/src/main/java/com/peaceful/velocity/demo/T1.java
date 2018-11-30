package com.peaceful.velocity.demo;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;

/**
 * Created by wangjun38 on 2018/5/4.
 */
public class T1 {

    public static void main(String[] args) {
        // 设置资源文件载入方式，从文件路径中载入  也可以设置从classpath中载入
        // Velocity.setProperty(RuntimeConstants.RESOURCE_LOADER, "file");
        // Velocity.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH,"/");
        Velocity.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        Velocity.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        Velocity.init();
        VelocityContext context = new VelocityContext();
        context.put("name", "Jun");
        StringWriter writer = new StringWriter();
        Velocity.mergeTemplate("index.vm", "UTF-8", context, writer);
        System.out.print(writer.toString());

        Velocity.mergeTemplate("index.html", "UTF-8", context, writer);
        System.out.print(writer.toString());
    }
}

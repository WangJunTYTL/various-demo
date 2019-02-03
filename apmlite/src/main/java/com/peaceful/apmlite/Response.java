package com.peaceful.apmlite;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Created by wangjun on 2018-12-19.
 */
public class Response {

    static {
        Velocity.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        Velocity.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        Velocity.init();
    }

    public static String getTemplate(String template){
        return getTemplate(template,null);
    }

    public static String getTemplate(String template, Map<String, Object> data) {
        Context context = new VelocityContext();
        if (data == null || data.size() == 0) {
            // nothing
        } else {
            data.forEach((k, v) -> context.put(k, v));
        }

        Writer writer = new StringWriter();
        Velocity.getTemplate(template).merge(context, writer);
        return writer.toString();
    }
}

package com.peaceful.apmlite;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangjun on 2018-12-19.
 */
public class Response {

    static {
        Velocity.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        Velocity.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        Velocity.init();
    }

    public static void Template(ServletResponse response, String template) throws IOException {
        Response.Template(response, template, null);
    }

    public static void Template(ServletResponse response, String template, Map<String, Object> data) throws IOException {
        Context context = new VelocityContext();
        if (data == null || data.size() == 0) {
            // nothing
        } else {
            data.forEach((k, v) -> context.put(k, v));
        }

        Writer writer = new StringWriter();
        Velocity.getTemplate(template,"UTF-8").merge(context, writer);
        HttpServletResponse _response = (HttpServletResponse) response;
        _response.setContentType("text/html;charset=UTF-8");
        PrintWriter _writer = _response.getWriter();
        _writer.write(writer.toString());
    }


    public static void Json(ServletResponse response, int code, String message) throws IOException {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{").append("'").append("code").append("'").append(":").append(code);
        buffer.append(",").append("'").append("message").append("'").append(":").append("'").append(message).append("'");
        buffer.append("}");
        HttpServletResponse _response = (HttpServletResponse) response;
        _response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println(buffer.toString());
    }
}

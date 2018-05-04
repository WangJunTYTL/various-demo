package com.peaceful.spring.boot.web.demo;

/**
 * Created by wangjun38 on 2018/5/4.
 */

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.StringWriter;

@RestController
public class HelloController {

    @RequestMapping("/index")
    public String index() {
        Velocity.setProperty(org.apache.velocity.runtime.RuntimeConstants.RESOURCE_LOADER, "classpath");
        Velocity.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        Velocity.init();
        VelocityContext context = new VelocityContext();
        context.put("name", "Jun");
        StringWriter writer = new StringWriter();
        Velocity.mergeTemplate("static/index.vm", "UTF-8", context, writer);
        return writer.toString();
    }

}

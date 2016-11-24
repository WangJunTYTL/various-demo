package com.peaceful.jexl.demo;

import org.apache.commons.jexl3.*;
import org.slf4j.helpers.Util;

import java.io.File;

/**
 * Created by wangjun on 16/9/4.
 */
public class JexlScriptDemo {

    private static JexlEngine jexl = new JexlBuilder().create();

    public static void main(String[] args) {
        // 返回基本对象
        JexlScript script = jexl.createScript(new File("jexl.el"));
        JexlContext context = new MapContext();
        context.set("G1", 4);
        context.set("G2", 3);
        Object o = script.execute(context);
        Util.report(o.toString());

        // 返回自定义对象
        script = jexl.createScript(new File("jexl02.el"));
        context = new MapContext();
        context.set("name", "WJ");
        context.set("age", 28);
        context.set("user", new User());
        o = script.execute(context);
        Util.report(o.toString());
    }
}

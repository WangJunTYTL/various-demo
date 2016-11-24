package com.peaceful.jexl.demo;

import org.apache.commons.jexl3.*;
import org.slf4j.helpers.Util;

import java.io.File;

/**
 * Created by wangjun on 16/9/4.
 */
public class JexlScriptDemo02 {

    private static JexlEngine jexl = new JexlBuilder().create();

    public static void main(String[] args) {
        // 返回基本对象
        JexlScript script = jexl.createScript(new File("alert.el"));
        JexlContext context = new MapContext();
        context.set("G1", 4);
        context.set("G2", 3);
        context.set("alert", new Alert());
        Alert alert = (Alert) script.execute(context);
        Util.report(alert.toString());
    }
}

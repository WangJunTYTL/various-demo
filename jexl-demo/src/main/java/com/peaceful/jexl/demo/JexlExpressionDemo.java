package com.peaceful.jexl.demo;

import com.google.common.collect.Maps;
import org.apache.commons.jexl3.*;
import org.slf4j.helpers.Util;

import java.util.Map;

/**
 * http://commons.apache.org/proper/commons-jexl/reference/syntax.html#Language_Elements
 * Created by wangjun on 16/9/4.
 */
public class JexlExpressionDemo {

    private static JexlBuilder builder = new JexlBuilder();
    private static final JexlEngine jexl = builder.cache(512).strict(true).silent(false).create();

    public static void main(String[] args) {
        // Assuming we have a JexlEngine instance initialized in our class named 'jexl':
        // Create an expression object for our calculation
        String calculateTax = "((G1 + G2 + G3) * 0.1) + G4"; //e.g. "((G1 + G2 + G3) * 0.1) + G4";
        JexlExpression e = jexl.createExpression( calculateTax );

        // populate the context
        JexlContext context = new MapContext();
        context.set("G1", 1);
        context.set("G2", 1);
        context.set("G3", 1);
        context.set("G4", 1);
        // ...

        // work it out
        Number result = (Number) e.evaluate(context);
        Util.report(result.toString());

        JexlExpression ex = jexl.createExpression("`hello ${map.name}`");
        JexlContext context1 = new MapContext();
        Map<String,Object> map = Maps.newHashMap();
        map.put("name","WJ");
        context1.set("map", map);
        Util.report((String) ex.evaluate(context1));
    }

}

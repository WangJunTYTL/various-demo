package com.peaceful.demo;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.util.HashMap;
import java.util.Map;

/**
 * Run a script under examples folder.
 *
 * @author dennis(killme2008@gmail.com)
 *
 */
public class RunScriptExample {

    public static void main(final String[] args) throws Exception {
        // Compile the script into a Expression instance.
        Expression exp = AviatorEvaluator.getInstance().compileScript("hello.av");
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("scene",101);
        int[] a = {1,2};
        params.put("verifyModelList",a);
//        exp.newEnv("scene",101,"verifyModelList",a);
        // Run the exprssion.
        System.out.println(exp.execute(params));

    }

}
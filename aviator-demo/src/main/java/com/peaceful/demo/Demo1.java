package com.peaceful.demo;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.io.IOException;

public class Demo1 {

    public static void main(String[] args) throws IOException {
        // Compile the script into a Expression instance.
        Expression exp = AviatorEvaluator.getInstance().compileScript("Demo1.av");
        // Run the exprssion.
        exp.execute();
    }
}

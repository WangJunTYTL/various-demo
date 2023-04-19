package com.peaceful.jdk.demo;

import com.peaceful.Util;

import java.io.Console;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by wangjun on 15/3/28.
 */
public class SystemTest {

    public static void main(String[] args) {
        Map env = System.getenv();
        Util.report(env);
        Properties properties = System.getProperties();
        Util.report(properties);
        Util.report("os.name:" + properties.get("os.name"));

        Util.report("----------console");
        console();

        in();

//        System.gc();

    }

    public static String getProperties(String key) {
        if (key == null) return null;
        System.getProperties();
        return System.getProperty(key);
    }

    public static void console() {
        Console console = System.console();
        if (console == null) {
            Util.report("console is null");
        } else {
            PrintWriter writer = console.writer();
            writer.print("hello world");

        }
    }

    public static void in(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请随意输入字符...");
        while (scanner.hasNext()){
            System.out.println(scanner.nextLine());
        }
    }

    public static void time(){
        System.currentTimeMillis();
        System.nanoTime();
    }

    public static void copyArray(){
//        System.arraycopy();
    }
}

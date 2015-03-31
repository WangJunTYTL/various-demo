package com.peaceful.cglib.demo.asm;

import com.peaceful.common.util.Util;
import net.sf.cglib.asm.ClassReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <a mailto:wangjuntytl@163.com>Email:wangjuntytl@163.com</a>
 *
 * @author wangjun
 * @version 1.0
 * @since 15/3/29.
 */

public class T1 {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        InputStream inputStream = new FileInputStream(new File("/Users/wangjun/ideaWorkSpace/github/myapp/java-demo/target/classes/com/peaceful/jdk/demo/T1.class"));
        ClassReader classReader = new ClassReader(inputStream);
        //读取class文件分析类，得到一些类的信息
        Util.report(classReader.getClassName());
        Util.report(classReader.getSuperName());

    }
}

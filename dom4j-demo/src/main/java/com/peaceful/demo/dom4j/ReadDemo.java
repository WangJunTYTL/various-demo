package com.peaceful.demo.dom4j;

import com.peaceful.common.util.Util;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by wangjun on 15/1/10.
 */
public class ReadDemo {

    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        SAXReader saxReader = new SAXReader();
        String path = System.getProperty("user.dir", "<NA>")+"/target/classes/Test.tld";
        FileInputStream inputStream = new FileInputStream(new File(path));
        Document document = saxReader.read(inputStream, "GBK");
        Element rootElement = document.getRootElement();
        String varsion = rootElement.elementText("tlib-version");
        Util.report(varsion);
    }
}


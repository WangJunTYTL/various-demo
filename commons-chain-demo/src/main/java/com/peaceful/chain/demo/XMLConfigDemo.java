package com.peaceful.chain.demo;

import org.apache.commons.chain.Catalog;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.config.ConfigParser;
import org.apache.commons.chain.impl.CatalogFactoryBase;
import org.apache.commons.chain.impl.ContextBase;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/2
 * @since 1.6
 */

public class XMLConfigDemo {

    private static final String CONFIG_FILE = "abc.xml";
    static ConfigParser parser =  new ConfigParser();


    public static void main(String[] args) throws Exception {
        parser.parse(XMLConfigDemo.class.getClassLoader().getResource(CONFIG_FILE));
        Catalog catalog = CatalogFactoryBase.getInstance().getCatalog("abc");
        Command command = catalog.getCommand("abc-hand");
        ContextBase contextBase = new ContextBase();
        command.execute(contextBase);

    }
}

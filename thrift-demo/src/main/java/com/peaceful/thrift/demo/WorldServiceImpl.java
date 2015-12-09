package com.peaceful.thrift.demo;

import org.apache.thrift.TException;
import service.demo.World;

/**
 * @author wangjun
 * @version 15/12/5
 */
public class WorldServiceImpl implements World.Iface{

    @Override
    public String say(String about) throws TException {
        return about;
    }

}

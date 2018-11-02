package com.peaceful.thrift.demo;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TMemoryBuffer;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.demo.EnumObj;
import service.demo.FirstRequest;

/**
 * Created by Jun on 2018/8/31.
 */
public class TJsonDemo {

    public static void main(String[] args) throws TException, UnsupportedEncodingException {
        TMemoryBuffer buffer = new TMemoryBuffer(1024);
        TProtocol jsonProtocol = new TJSONProtocol.Factory().getProtocol(buffer);

        // 模拟数据序列化过程
        FirstRequest request = new FirstRequest();
        request.setNumber(1);
        request.setMsg("hello");

        // List序列化方式
        List<Integer> integerList = new ArrayList<Integer>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        request.setListObj(integerList);

        // Map
        Map<String,String> mapObj = new HashMap<String, String>();
        mapObj.put("a","1");
        mapObj.put("b","2");
        mapObj.put("c","3");
        request.setMapObj(mapObj);

        request.setEnumObj(EnumObj.BAR);

        request.write(jsonProtocol);

        // 模拟对象序列化过程
        System.out.println(buffer.toString("UTF-8"));
        request.read(jsonProtocol);

    }
}

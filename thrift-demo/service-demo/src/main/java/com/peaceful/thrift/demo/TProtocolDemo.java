package com.peaceful.thrift.demo;

import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.protocol.TSimpleJSONProtocol;
import org.apache.thrift.protocol.TTupleProtocol;
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
public class TProtocolDemo {

    public static void main(String[] args) throws TException, UnsupportedEncodingException {
        TMemoryBuffer buffer = new TMemoryBuffer(1024);
        TProtocol jsonProtocol = new TJSONProtocol.Factory().getProtocol(buffer);

        TSerializer serializer = new TSerializer(new TJSONProtocol.Factory());

        // 对象
        FirstRequest request = new FirstRequest();
        request.setNumber(1);
        request.setMsg("hello");

        // List数据
        List<Integer> integerList = new ArrayList<Integer>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        request.setListObj(integerList);

        // Map数据
        Map<String,String> mapObj = new HashMap<String, String>();
        mapObj.put("a","1");
        mapObj.put("b","2");
        mapObj.put("c","3");
        request.setMapObj(mapObj);

        request.setEnumObj(EnumObj.BAR);

        request.write(jsonProtocol);

        // 模拟对象序列化过程
        System.out.println("serializer:"+buffer.toString("UTF-8"));
        // 同上面方式一样,同样是调用对象的read write方法
        System.out.println("TSimpleJSONProtocol:"+new TSerializer(new TSimpleJSONProtocol.Factory()).toString(request));
        System.out.println("TJSONProtocol:"+serializer.toString(request));

        optputData(request,new TBinaryProtocol.Factory());
        optputData(request,new TCompactProtocol.Factory());
        optputData(request,new TTupleProtocol.Factory());
        optputData(request,new TCustomerProtocol.Factory());
        request.read(jsonProtocol);

    }

    public static void optputData(FirstRequest request, TProtocolFactory factory) throws TException {
        TSerializer s2 = new TSerializer(factory);
        byte[] bytes = s2.serialize(request);
        StringBuilder b2 = new StringBuilder();
        for (int i=0;i<bytes.length;i++){
            b2.append(bytes[i]);
        }
        System.out.println(factory.getProtocol(null).getClass().getSimpleName()+":"+b2);
    }
}

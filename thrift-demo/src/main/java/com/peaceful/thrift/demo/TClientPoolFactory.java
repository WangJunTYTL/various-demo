package com.peaceful.thrift.demo;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import service.demo.HelloServer;

/**
 * Created by Jun on 2018-12-02.
 */
public class TClientPoolFactory extends BasePooledObjectFactory<HelloServer.Iface> {


    @Override
    public HelloServer.Iface create() throws Exception {
        TTransport transport = new TFramedTransport(new TSocket("localhost", 9090));  //非阻塞
//        TTransport transport = new TSocket("localhost", 9090);
        transport.open();

        TProtocol protocol = new TBinaryProtocol(transport);

        HelloServer.Client client = new HelloServer.Client(protocol);

        return client;
    }

    @Override
    public PooledObject<HelloServer.Iface> wrap(HelloServer.Iface iface) {
        return new DefaultPooledObject<HelloServer.Iface>(iface);
    }

    @Override
    public void destroyObject(PooledObject<HelloServer.Iface> p) throws Exception {
        super.destroyObject(p);
    }

    @Override
    public boolean validateObject(PooledObject<HelloServer.Iface> p) {
        return super.validateObject(p);
    }
}

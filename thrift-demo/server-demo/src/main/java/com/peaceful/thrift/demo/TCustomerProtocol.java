package com.peaceful.thrift.demo;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TTransport;

/**
 * Created by Jun on 2018/11/29.
 */
public class TCustomerProtocol extends TBinaryProtocol {

    public TCustomerProtocol(TTransport trans) {
        super(trans);
    }

    public static class Factory implements TProtocolFactory {

        @Override
        public TProtocol getProtocol(TTransport trans) {
            return new TCustomerProtocol(trans);
        }
    }


    @Override
    public void writeMessageBegin(TMessage message) throws TException {
        System.out.println("写入数据");
        byte[] bytes = new byte[1];
        bytes[0] = 1;
        trans_.write(bytes);
        super.writeMessageBegin(message);
    }

    @Override
    public TMessage readMessageBegin() throws TException {
        byte[] bytes = new byte[1];
        trans_.read(bytes, 0, 1);
        System.out.println("读取数据");
        return super.readMessageBegin();
    }
}

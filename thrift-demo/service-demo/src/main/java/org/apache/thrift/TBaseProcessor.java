package org.apache.thrift;

import java.util.Map;

import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.protocol.TMessageType;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TType;

public abstract class TBaseProcessor<I> implements TProcessor {
  private final I iface;
  private final Map<String,ProcessFunction<I, ? extends TBase>> processMap;

  protected TBaseProcessor(I iface, Map<String, ProcessFunction<I, ? extends TBase>> processFunctionMap) {
    this.iface = iface;
    this.processMap = processFunctionMap;
  }

  @Override
  public boolean process(TProtocol in, TProtocol out) throws TException {
    // 服务端执行过程
    // 读message头部信息TMessage
    TMessage msg = in.readMessageBegin();
    // 得到对应的方法，每一个方法会被包装成一个ProcessFunction对象，所有的ProcessFunction实例会被放入到一个Map集合
    ProcessFunction fn = processMap.get(msg.name);
    // 调用方法不存在
    if (fn == null) {
      TProtocolUtil.skip(in, TType.STRUCT);
      in.readMessageEnd();
      TApplicationException x = new TApplicationException(TApplicationException.UNKNOWN_METHOD, "Invalid method name: '"+msg.name+"'");
      out.writeMessageBegin(new TMessage(msg.name, TMessageType.EXCEPTION, msg.seqid));
      x.write(out);
      out.writeMessageEnd();
      out.getTransport().flush();
      return true;
    }
    //  执行方法，并将结果写入到网络
    fn.process(msg.seqid, in, out, iface);
    return true;
  }
}

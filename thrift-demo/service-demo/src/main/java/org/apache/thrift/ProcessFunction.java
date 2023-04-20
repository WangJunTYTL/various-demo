/**
 * 
 */
package org.apache.thrift;

import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.protocol.TMessageType;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;

public abstract class ProcessFunction<I, T extends TBase> {
  private final String methodName;

  public ProcessFunction(String methodName) {
    this.methodName = methodName;
  }

  public final void process(int seqid, TProtocol iprot, TProtocol oprot, I iface) throws TException {
    T args = getEmptyArgsInstance();
    try {
      // 反序列化参数对象
      args.read(iprot);
    } catch (TProtocolException e) {
      iprot.readMessageEnd();
      TApplicationException x = new TApplicationException(TApplicationException.PROTOCOL_ERROR, e.getMessage());
      oprot.writeMessageBegin(new TMessage(getMethodName(), TMessageType.EXCEPTION, seqid));
      x.write(oprot);
      oprot.writeMessageEnd();
      oprot.getTransport().flush();
      return;
    }
    iprot.readMessageEnd();
    // 执行服务端方法，会调用通过idl生成的服务端代码
    TBase result = getResult(iface, args);
    // 响应.应答
    // 写入头部消息TMessage
    oprot.writeMessageBegin(new TMessage(getMethodName(), TMessageType.REPLY, seqid));
    // 写入结果
    result.write(oprot);
    oprot.writeMessageEnd();
    // flush stream
    oprot.getTransport().flush();
  }

  // 客户端实现，会通过idl编译器自动生成，服务端会更改方法名称找到对应的执行方法
  protected abstract TBase getResult(I iface, T args) throws TException;

  protected abstract T getEmptyArgsInstance();

  public String getMethodName() {
    return methodName;
  }
}
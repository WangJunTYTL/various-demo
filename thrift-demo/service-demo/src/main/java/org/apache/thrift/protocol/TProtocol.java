/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.thrift.protocol;

import java.nio.ByteBuffer;

import org.apache.thrift.TException;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.transport.TTransport;

/**
 * 定义各种类型应该如何编码和解码，是序列化和反序列化的核心抽象
 *
 * Protocol interface definition.
 *
 */
public abstract class TProtocol {

  /**
   * Prevent direct instantiation
   */
  @SuppressWarnings("unused")
  private TProtocol() {}

  /**
   * Transport
   */
  protected TTransport trans_;

  /**
   * Constructor
   */
  protected TProtocol(TTransport trans) {
    trans_ = trans;
  }

  /**
   * Transport accessor
   */
  public TTransport getTransport() {
    return trans_;
  }

  /**
   * Writing methods.
   */
  // TMessage 请求和响应头部信息，用于描述调用的方法name，调用过程：call、reply、exception ，客户端调用自增id
  public abstract void writeMessageBegin(TMessage message) throws TException;

  public abstract void writeMessageEnd() throws TException;

  // TStruct  描述对象
  public abstract void writeStructBegin(TStruct struct) throws TException;

  public abstract void writeStructEnd() throws TException;

  // TField 描述属性，包含属性的id、name、type
  public abstract void writeFieldBegin(TField field) throws TException;

  public abstract void writeFieldEnd() throws TException;

  public abstract void writeFieldStop() throws TException;

  // TMap 描述Map类型，keyType，valueType，size
  public abstract void writeMapBegin(TMap map) throws TException;

  public abstract void writeMapEnd() throws TException;

  // TList 描述List类型，elementType，size
  public abstract void writeListBegin(TList list) throws TException;

  public abstract void writeListEnd() throws TException;

  public abstract void writeSetBegin(TSet set) throws TException;

  public abstract void writeSetEnd() throws TException;

  public abstract void writeBool(boolean b) throws TException;

  public abstract void writeByte(byte b) throws TException;

  public abstract void writeI16(short i16) throws TException;

  public abstract void writeI32(int i32) throws TException;

  public abstract void writeI64(long i64) throws TException;

  public abstract void writeDouble(double dub) throws TException;

  public abstract void writeString(String str) throws TException;

  public abstract void writeBinary(ByteBuffer buf) throws TException;

  /**
   * Reading methods.
   */

  public abstract TMessage readMessageBegin() throws TException;

  public abstract void readMessageEnd() throws TException;

  public abstract TStruct readStructBegin() throws TException;

  public abstract void readStructEnd() throws TException;

  public abstract TField readFieldBegin() throws TException;

  public abstract void readFieldEnd() throws TException;

  public abstract TMap readMapBegin() throws TException;

  public abstract void readMapEnd() throws TException;

  public abstract TList readListBegin() throws TException;

  public abstract void readListEnd() throws TException;

  public abstract TSet readSetBegin() throws TException;

  public abstract void readSetEnd() throws TException;

  public abstract boolean readBool() throws TException;

  public abstract byte readByte() throws TException;

  public abstract short readI16() throws TException;

  public abstract int readI32() throws TException;

  public abstract long readI64() throws TException;

  public abstract double readDouble() throws TException;

  public abstract String readString() throws TException;

  public abstract ByteBuffer readBinary() throws TException;

  /**
   * Reset any internal state back to a blank slate. This method only needs to
   * be implemented for stateful protocols.
   */
  public void reset() {}
  
  /**
   * Scheme accessor
   */
  public Class<? extends IScheme> getScheme() {
    return StandardScheme.class;
  }
}

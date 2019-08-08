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

package org.apache.thrift.transport;

/**
 * 服务端监听、建立连接抽象，建立连接返回TTransport实例
 *
 * Server transport. Object which provides client transports.
 */
public abstract class TServerTransport {

    /**
     * 监听服务连接
     */
    public abstract void listen() throws TTransportException;

    public final TTransport accept() throws TTransportException {
        // 等同于一个连接实例
        TTransport transport = acceptImpl();
        if (transport == null) {
            throw new TTransportException("accept() may not return NULL");
        }
        return transport;
    }

    public abstract void close();

    /**
     * 建立连接，返回TTransport实例
     */
    protected abstract TTransport acceptImpl() throws TTransportException;

    /**
     * Optional method implementation. This signals to the server transport that it should break out
     * of any accept() or listen() that it is currently blocked on. This method, if implemented,
     * MUST be thread safe, as it may be called from a different thread context than the other
     * TServerTransport methods.
     */
    public void interrupt() {
    }

}

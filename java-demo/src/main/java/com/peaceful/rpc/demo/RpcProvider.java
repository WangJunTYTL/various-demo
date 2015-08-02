package com.peaceful.rpc.demo;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/1
 * @since 1.6
 */

public class RpcProvider {

    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        RpcFramework.export(service, 1234);
    }

}

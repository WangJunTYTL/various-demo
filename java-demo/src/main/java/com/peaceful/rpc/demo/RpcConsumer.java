package com.peaceful.rpc.demo;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/1
 * @since 1.6
 */

public class RpcConsumer {

    public static void main(String[] args) throws Exception {
        HelloService service = RpcFramework.refer(HelloService.class, "127.0.0.1", 1234);
        for (int i = 0; i < Integer.MAX_VALUE; i ++) {
            String hello = service.hello("World" + i);
            System.out.println(hello);
            Thread.sleep(1000);
        }
    }

}
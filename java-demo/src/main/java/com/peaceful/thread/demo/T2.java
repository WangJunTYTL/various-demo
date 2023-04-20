package com.peaceful.thread.demo;

/**
 * Created by wangjun38 on 2019-10-31.
 */
public class T2 {

    public static void main(String[] args) {
        Request request = new Request();
        Request2 request2 = new Request2();

        request.set("a");

        request.get();
        request2.get();

    }

    private static class Request{

        private final ThreadLocal<String> local = new ThreadLocal<>();


        public void set(String v){
            local.set(v);
        }

        public void get(){
            System.out.println(local.get());
            System.out.println(Thread.currentThread());
        }
    }

    private static class Request2{

        private final ThreadLocal<String> local = new ThreadLocal<>();


        public void set(String v){
            local.set(v);
        }

        public void get(){
            System.out.println(local.get());
            System.out.println(Thread.currentThread());
        }
    }
}

package com.peaceful.concurrent.demo;

import com.peaceful.Util;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by wangjun on 15/2/5.
 */
public class FutureModelTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Client client = new Client(1);
        FutureTask<String> futureTask = new FutureTask<String>(client);
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(futureTask);
        Util.report("client request complete...");
        //client 请求在后台运行，接着继续other job
        Thread.sleep(2000);
        //其它job完成，调用get将会出现阻塞
        Util.report("other job complete...") ;
        Util.report(futureTask.get());
    }
}

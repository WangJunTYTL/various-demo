package com.peaceful.concurrent.demo;

import com.peaceful.common.util.Util;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by wangjun on 15/2/5.
 */
public class MasterWorkerModelTest {

    // 利用future实现master - worker 模式
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        Client a = new Client(1);
        Client b = new Client(1);
        Client c = new Client(1);
        FutureTask<String> futureTaskA = new FutureTask<String>(a);
        FutureTask<String> futureTaskB = new FutureTask<String>(b);
        FutureTask<String> futureTaskC = new FutureTask<String>(c);
        executorService.submit(futureTaskA);
        executorService.submit(futureTaskB);
        executorService.submit(futureTaskC);
        Util.report("client request complete...");
        //client 请求在后台运行，接着继续other job
        Thread.sleep(2000);
        //其它job完成，调用get将会出现阻塞
        Util.report("other job complete...") ;
        Util.report("结果汇总："+futureTaskA.get()+"\n"+futureTaskB.get()+"\n"+futureTaskC.get());
    }
}

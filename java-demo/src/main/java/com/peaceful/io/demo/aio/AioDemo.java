package com.peaceful.io.demo.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by wangjun on 16/2/24.
 */
public class AioDemo {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        String userDir= System.getProperty("user.dir");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(Paths.get(userDir+ "/data/nio-data.txt"));
        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        completionHandlerDemo(channel,buffer);
        futureDemo(channel,buffer);

    }

    private static void completionHandlerDemo(AsynchronousFileChannel afc, ByteBuffer byteBuffer) throws IOException {
        final Thread current = Thread.currentThread();
        afc.read(byteBuffer, 0, null, new CompletionHandler<Integer, Object>() {
            @Override
            public void completed(Integer result, Object attachment) {
                System.out.println("Bytes Read = " + result);
                current.interrupt();
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println(exc.getCause());
                current.interrupt();
            }
        });
        System.out.println("Waiting for completion...");
        try {
            current.join();
        } catch (InterruptedException e) {
        }
        System.out.println("End");
        afc.close();
    }

    private static void futureDemo(AsynchronousFileChannel afc, ByteBuffer byteBuffer) throws InterruptedException, ExecutionException, IOException {
        Future<Integer> result = afc.read(byteBuffer, 0);
        while (!result.isDone()) {
            System.out.println("Waiting file channel finished....");
            Thread.sleep(1);
        }
        System.out.println("Finished? = " + result.isDone());
        System.out.println("byteBuffer = " + result.get());
        System.out.println(byteBuffer);
        afc.close();
    }
}

package com.peaceful.thread.demo.thread.pool;

import java.util.concurrent.TimeUnit;

/**
 * Created by JunWang on 2016/12/9.
 */
public class BlockQueueSetup {

    public static void main(String[] args) throws InterruptedException {
        BlockQueue<Integer> blockQueue = new BlockQueue<Integer>(8);
        new Consumer(blockQueue).start();
        for (int i = 0;i<100;i++){
            blockQueue.put(i);
        }
    }

    public static class Consumer extends Thread{

        private BlockQueue<Integer> blockQueue;
        public Consumer(BlockQueue<Integer>  blockQueue){
            this.blockQueue = blockQueue;
        }
        @Override
        public void run() {
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("consumer->"+blockQueue.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

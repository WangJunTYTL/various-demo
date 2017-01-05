package com.peaceful.collection.demo.test.pv;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by JunWang on 2016/11/21.
 */
public class TopN {


    private static ArrayBlockingQueue<String> urls = new ArrayBlockingQueue<String>(1000000);

    public static void main(String[] args) throws InterruptedException {
        runProducer();
        new Print().start();
    }

    // 按照设定的时间频率，把数据从队列中拖拽到另外的一个集合里，然后异步分析该集合数据
    public static List<String> drain() throws InterruptedException {
        if (urls.isEmpty()) return Lists.newArrayList();
        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();
        List<String> coll = new LinkedList<String>();
        while (end - start <= 1000) {
            if (urls.isEmpty()) {
                String url = urls.poll(1, TimeUnit.SECONDS);
                if (url != null) {
                    coll.add(url);
                }
            }
            urls.drainTo(coll, Integer.MAX_VALUE);
            end = System.currentTimeMillis();
        }
        return coll;
    }

    // 获取统计结果
    private static Map<String, Long> counts = new HashMap<String, Long>();

    private static void incr(List<String> coll) {
        if (coll.isEmpty()) {
            return;
        }
        for (String url : coll) {
            if (counts.containsKey(url)) {
                counts.put(url, 1 + counts.get(url));
            } else {
                counts.put(url, 1L);
            }
        }
    }

    // 排序比较器
    private static Comparator<Visitor> comparator = new Comparator<Visitor>() {
        @Override
        public int compare(Visitor o1, Visitor o2) {
            if (o1.count > o2.count) {
                return -1;
            } else if (o1.count == o2.count) {
                return 0;
            } else {
                return 1;
            }
        }
    };

    // 数据排序
    public static List<Visitor> sort() {
        List<Visitor> visitorList = new LinkedList<TopN.Visitor>();
        for (String key : counts.keySet()) {
            visitorList.add(new Visitor(key, counts.get(key)));
        }
        Collections.sort(visitorList, comparator);
        return visitorList;
    }

    private static class Print extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    incr(drain());
                    List<Visitor> visitors = sort();
                    int i = 1;
                    for (Visitor visitor : visitors) {
                        if (i < 11) {
                            System.out.println("No_" + i + ":" + visitor.url + ":" + visitor.count);
                            i++;
                        } else {
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Visitor {


        public String url;
        public Long count;

        public Visitor(String url) {
            this.url = url;
            this.count = 0L;
        }

        public Visitor(String url, Long count) {
            this.url = url;
            this.count = count;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof VisitorPV)) return false;

            VisitorPV visitorPV = (VisitorPV) o;

            return url.equals(visitorPV.url);

        }

        @Override
        public int hashCode() {
            return url.hashCode();
        }
    }


    public static void runProducer() throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                while (true) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < 3; i++) {
                        stringBuilder.append(random.nextInt(5));
                    }
//                    System.out.println("Put:"+stringBuilder.toString());
                    urls.offer(stringBuilder.toString());
                    urls.offer("888");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

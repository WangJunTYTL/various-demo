package com.peaceful.apmlite;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangjun on 2019-02-01.
 */
public class MyRDBTest {

    @org.junit.Test
    public void getAllTags() throws IOException, SQLException, InterruptedException {

        MyRDB myRDB = new MyRDB("mysql");
        MyTimingTask.setUp(myRDB);
        MyWebServer.setUp(myRDB);

        myRDB.insertTag("tag", MetricNum.Metric_01);
        System.out.println(myRDB.getAllTags());

        Metric01 metric01 = new Metric01();
        metric01.setTag("tag");
        metric01.setCount(100);
        metric01.setMax(100);
        metric01.setMin(100);
        metric01.setAvg(100);

        myRDB.insertMetric(MetricNum.Metric_01, metric01.getMapData());

        System.out.println(myRDB.getMetric01ByTag("tag", MetricNum.Metric_01, System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(1), System.currentTimeMillis()));

        TimeUnit.MINUTES.sleep(60);


    }

    @Test
    public void Start(){
        String name = MyStopWatch.DEFAULT_LOGGER_NAME;

    }
}
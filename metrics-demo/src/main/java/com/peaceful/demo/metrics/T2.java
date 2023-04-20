package com.peaceful.demo.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangjun38 on 2019-10-30.
 */
public class T2 {

    static final MetricRegistry REGISTRY = new MetricRegistry();

    public static void main(String[] args) {
        startReport();
        REGISTRY.register("a", new Gauge<String>() {

            @Override
            public String getValue() {
                return "hello";
            }
        });
        wait5Seconds();

    }

    static void startReport() {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(REGISTRY)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);
    }

    static void wait5Seconds() {
        try {
            Thread.sleep(5*1000);
        }
        catch(InterruptedException e) {}
    }
}

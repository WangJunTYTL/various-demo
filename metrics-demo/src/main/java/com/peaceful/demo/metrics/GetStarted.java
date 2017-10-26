package com.peaceful.demo.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.JmxAttributeGauge;
import com.codahale.metrics.JvmAttributeGaugeSet;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.TimeUnit;

/**
 * @author WangJun
 * @version 1.0 16/6/6
 */
public class GetStarted {
    static final MetricRegistry metrics = new MetricRegistry();
    static boolean flag = true;
    public static void main(String args[]) throws InterruptedException {
        startReport();
        while (flag) {
            Meter requests = metrics.meter("requests");
            requests.mark();
            System.out.println("count:"+requests.getCount());
            System.out.println("MeanRate:"+requests.getMeanRate());
            wait5Seconds();
        }
    }

    static void startReport() {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.MINUTES);
    }

    static void wait5Seconds() throws InterruptedException {
            Thread.sleep(1000);
    }
}

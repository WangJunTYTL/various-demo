package com.peaceful.apmlite;

/**
 * Created by wangjun on 2019-02-02.
 */
public enum MetricNum {
    Metric_01(01), // basic
    Metric_02(02), // tp99、tp999、tp9999
    Metric_11(11); // jvm

    public int num;


    MetricNum(int num) {
        this.num = num;
    }

    public static MetricNum findByValue(int num) {
        for (MetricNum metricNum : MetricNum.values()) {
            if (num == metricNum.num) {
                return metricNum;
            }
        }
        throw new IllegalArgumentException(num + " is illegal");
    }
}

package com.peaceful.apmlite;

/**
 * Created by wangjun on 2019-02-01.
 */
public class Metric02 {

    private int count;
    private double mean;
    private long max;
    private long min;
    private long tp95;
    private long tp99;
    private long tp999;
    private long tp9999;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public long getTp95() {
        return tp95;
    }

    public void setTp95(long tp95) {
        this.tp95 = tp95;
    }

    public long getTp99() {
        return tp99;
    }

    public void setTp99(long tp99) {
        this.tp99 = tp99;
    }

    public long getTp999() {
        return tp999;
    }

    public void setTp999(long tp999) {
        this.tp999 = tp999;
    }

    public long getTp9999() {
        return tp9999;
    }

    public void setTp9999(long tp9999) {
        this.tp9999 = tp9999;
    }
}

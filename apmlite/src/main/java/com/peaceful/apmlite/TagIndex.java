package com.peaceful.apmlite;

import java.sql.Timestamp;

/**
 * Created by wangjun on 2019-02-02.
 */
public class TagIndex {

    private long id;
    private String tag;
    private MetricNum metricNum;
    private Timestamp updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public MetricNum getMetricNum() {
        return metricNum;
    }

    public void setMetricNum(MetricNum metricNum) {
        this.metricNum = metricNum;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}

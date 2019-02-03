package com.peaceful.apmlite;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangjun on 2019-02-01.
 */
public class Metric01 {

    private String tag;
    private long count;
    private long avg;
    private long max;
    private long min;
    private Timestamp createTime;


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getAvg() {
        return avg;
    }

    public void setAvg(long avg) {
        this.avg = avg;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Map<String, Object> getMapData() {
        Map<String, Object> data = new HashMap<>();
        data.put("tag", tag);
        data.put("count", count);
        data.put("avg", avg);
        data.put("max", max);
        data.put("min", min);
        return data;
    }

    public void toBean(Map<String, Object> date) {
        setTag((String) date.get("tag"));
        setCount((Long) date.get("count"));
        setMax((Long) date.get("max"));
        setAvg((Long) date.get("avg"));
        setMin((Long) date.get("min"));
        setCreateTime((Timestamp) date.get("create_time"));
    }
}

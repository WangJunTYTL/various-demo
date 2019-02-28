package com.peaceful.apmlite;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangjun on 2019-02-02.
 */
public class MyGraph {

    public static Map<String, Object> getGraphDataForM01(List<Metric01> metric01List) {
        List<String> timeSeriesData = new ArrayList<>();
        List<Long> countAxisData = new ArrayList<>();
        List<Long> maxAxisData = new ArrayList<>();
        List<Long> minAxisData = new ArrayList<>();
        List<Long> avgAxisData = new ArrayList<>();
        for (Metric01 metric01 : metric01List) {
            timeSeriesData.add(DateHelper.formatDateTime(new Date(metric01.getCreateTime().getTime())));
            countAxisData.add(metric01.getCount());
            avgAxisData.add(metric01.getAvg());
            maxAxisData.add(metric01.getMax());
            minAxisData.add(metric01.getMin());
        }

        Map<String, Object> data = new HashMap<>();
        data.put("timeSeriesData", toJson(timeSeriesData));
        data.put("countAxisData", toJson(countAxisData));
        data.put("maxAxisData", toJson(maxAxisData));
        data.put("minAxisData", toJson(minAxisData));
        data.put("avgAxisData", toJson(avgAxisData));
        return data;
    }

    public static Map<String, Object> getGraphDataForM11(List<Metric11> metric11List) {

        List<String> timeSeriesData = new ArrayList<>();
        List<Long> heapMemoryAxisData = new ArrayList<>();
        List<Long> nonHeapMemoryAxisData = new ArrayList<>();
        List<Long> gcTimeAxisData = new ArrayList<>();
        List<Long> gcCountAxisData = new ArrayList<>();
        List<Long> threadCountAxisData = new ArrayList<>();

        for (Metric11 metric : metric11List) {
            timeSeriesData.add(DateHelper.formatDateTime(new Date(metric.getCreateTime().getTime())));
            heapMemoryAxisData.add(metric.getHeapMemory());
            nonHeapMemoryAxisData.add(metric.getNonHeapMemory());
            gcTimeAxisData.add(metric.getGcTime());
            gcCountAxisData.add(metric.getGcCount());
            threadCountAxisData.add(metric.getThreadCount());
        }

        Map<String, Object> data = new HashMap<>();
        data.put("timeSeriesData", toJson(timeSeriesData));
        data.put("heapMemoryAxisData", toJson(heapMemoryAxisData));
        data.put("nonHeapMemoryAxisData", toJson(nonHeapMemoryAxisData));
        data.put("gcTimeAxisData", toJson(gcTimeAxisData));
        data.put("gcCountAxisData", toJson(gcCountAxisData));
        data.put("threadCountAxisData", toJson(threadCountAxisData));
        return data;
    }


    private static String toJson(List data) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        int flag = 0;
        for (Object o : data) {
            if (o instanceof String) {
                stringBuffer.append("'").append(o).append("'");
            } else {
                stringBuffer.append(o);
            }
            flag++;

            if (flag != data.size()) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}

package com.peaceful.apmlite;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.perf4j.GroupedTimingStatistics;
import org.perf4j.TimingStatistics;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by wangjun on 2018-12-29.
 */
public class MyMemoryDB {

    private static List<String> timeX = new ArrayList<>();

    private static Map<String, List<String>> countDataSetY = new TreeMap<>();
    private static Map<String, List<String>> avgDataSetY = new TreeMap<>();
    private static Map<String, List<String>> maxDataSetY = new TreeMap<>();
    private static Map<String, List<String>> minDataSetY = new TreeMap<>();


    public static void save(GroupedTimingStatistics statistics) {

        if (statistics == null) {
            return;
        }

        long stopTime = statistics.getStopTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        timeX.add(dateFormat.format(new Date(stopTime)));

        Map<String, TimingStatistics> statisticsByTag = statistics.getStatisticsByTag();
        if (statisticsByTag == null || statisticsByTag.isEmpty()) {
            return;
        }

        statisticsByTag.forEach((k, v) -> {
            List<String> countData = countDataSetY.get(k);
            if (countData == null) {
                countData = new ArrayList<>();
            }
            countData.add(String.valueOf(v.getCount()));
            countDataSetY.put(k, countData);

            List<String> avgData = avgDataSetY.get(k);
            if (avgData == null) {
                avgData = new ArrayList<>();
            }
            avgData.add(String.valueOf(v.getMean()));
            avgDataSetY.put(k, avgData);

            List<String> maxData = maxDataSetY.get(k);
            if (maxData == null) {
                maxData = new ArrayList<>();
            }
            maxData.add(String.valueOf(v.getMax()));
            maxDataSetY.put(k, maxData);

            List<String> minData = minDataSetY.get(k);
            if (minData == null) {
                minData = new ArrayList<>();
            }
            minData.add(String.valueOf(v.getMin()));
            minDataSetY.put(k, minData);
        });
    }

    public static Map<String, String> toJson() {
        JSONArray timeXJson = new JSONArray();
        timeXJson.addAll(timeX);

        JSONArray countDataYJson = new JSONArray();
        JSONArray timeDataYJson = new JSONArray();

        countDataSetY.forEach((k, v) -> {
            JSONObject dataSet = new JSONObject();
            dataSet.put("label", k);
            dataSet.put("data", v);
            countDataYJson.add(dataSet);
        });

        avgDataSetY.forEach((k, v) -> {
            JSONObject dataSet = new JSONObject();
            dataSet.put("label", k);
            dataSet.put("data", v);
            timeDataYJson.add(dataSet);
        });

        minDataSetY.forEach((k, v) -> {
            JSONObject dataSet = new JSONObject();
            dataSet.put("label", k);
            dataSet.put("data", v);
            timeDataYJson.add(dataSet);
        });

        maxDataSetY.forEach((k, v) -> {
            JSONObject dataSet = new JSONObject();
            dataSet.put("label", k);
            dataSet.put("data", v);
            timeDataYJson.add(dataSet);
        });

        Map<String, String> data = new TreeMap<>();
        data.put("labels", timeXJson.toString());
        data.put("countDataSets", countDataYJson.toString());
        data.put("timeDataSets", timeDataYJson.toString());
        return data;
    }

}

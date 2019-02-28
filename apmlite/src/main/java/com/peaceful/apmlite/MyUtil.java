package com.peaceful.apmlite;

import com.google.common.base.Preconditions;

/**
 * Created by wangjun38 on 2019-02-04.
 */
public class MyUtil {

    public static String getMetricNumStr(MetricNum metricNum) {
        Preconditions.checkArgument(metricNum != null);
        String numStr = metricNum.name().split("_")[1];
        return numStr;
    }

    public static String getMetricDesc(MetricNum metricNum) {
        Preconditions.checkArgument(metricNum != null);
        if (metricNum == MetricNum.Metric_01) {
            return "自定义";
        } else if (metricNum == MetricNum.Metric_02) {
            return "自定义";
        } else if (metricNum == MetricNum.Metric_03) {
            return "接口";
        } else if (metricNum == MetricNum.Metric_11) {
            return "虚拟机";
        }
        return metricNum.name();
    }

}

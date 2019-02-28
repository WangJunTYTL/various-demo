package com.peaceful.apmlite;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangjun on 2018-12-19.
 */
public class MyServlet extends HttpServlet {

    private MyRDB rdb;

    public MyServlet(MyRDB rdb) {
        this.rdb = rdb;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> data = Maps.newHashMap();

        // 参数处理

        MetricNum metricNum = getMetricNum(req, resp, data);
        String tag = getTag(req, resp, metricNum);
        Preconditions.checkArgument(metricNum != null);
        Preconditions.checkArgument(tag != null);

        String page = getMetricPage(metricNum, tag, data);


        data.put("tagList", rdb.getTagList(metricNum));
        data.put("currentTag", tag);


        Response.Template(resp, page, data);
    }

    private MetricNum getMetricNum(HttpServletRequest req, ServletResponse resp, Map<String, Object> data) throws IOException {
        String metricNumStr = req.getParameter("metric");
        List<MetricNum> metricNumList = rdb.getAllMetrics();

        if (metricNumList.isEmpty()) {
            throw new APMException("没有可用的Metric");
        } else {
            data.put("metrics", metricNumList);
            data.put("metricsAsNum", metricNumList);
        }

        MetricNum metricNum = null;
        if ("01".equalsIgnoreCase(metricNumStr)) {
            metricNum = MetricNum.Metric_01;
        } else if ("03".equalsIgnoreCase(metricNumStr)) {
            metricNum = MetricNum.Metric_03;
        } else if ("11".equalsIgnoreCase(metricNumStr)) {
            metricNum = MetricNum.Metric_11;
        } else {
            throw new APMException("未知的Metric的编号：" + metricNumStr);
        }
        data.put("currentMetric", metricNum);
        data.put("MyUtil",MyUtil.class);
        return metricNum;
    }

    private String getTag(HttpServletRequest req, ServletResponse resp, MetricNum metricNum) throws IOException {
        String tag = req.getParameter("tag");
        if (StringUtils.isNotBlank(tag)) {
            return tag;
        }
        List<String> tagList = rdb.getTagList(metricNum);
        if (tagList.isEmpty()) {
            throw new APMException("Metric下没哟可用的Tag！");
        } else {
            return tagList.get(0);
        }
    }

    private String getMetricPage(MetricNum metricNum, String tag, Map<String, Object> data) {
        Preconditions.checkArgument(metricNum != null);
        Preconditions.checkArgument(StringUtils.isNotBlank(tag));

        // 查询对应Metric，并路由到对应GraphPage
        String page = "/static/graph2.vm";

        if (metricNum == MetricNum.Metric_01) {
            metricNum = MetricNum.Metric_01;
//            page = "/static/graph01.vm";
//            page = "/static/v3/index.vm";
            page = "/static/graph2.vm";
            List<Metric01> metric01List = rdb.getMetric01ByTag(tag, metricNum, System.currentTimeMillis() - TimeUnit.HOURS.toMillis(1), System.currentTimeMillis());
            data.putAll(MyGraph.getGraphDataForM01(metric01List));
        } else if (metricNum == MetricNum.Metric_03) {
            metricNum = MetricNum.Metric_03;
            page = "/static/graph03.vm";
            List<Metric01> metric01List = rdb.getMetric01ByTag(tag, metricNum, System.currentTimeMillis() - TimeUnit.DAYS.toMillis(7), System.currentTimeMillis());
            data.putAll(MyGraph.getGraphDataForM01(metric01List));
        } else if (metricNum == MetricNum.Metric_11) {
            metricNum = MetricNum.Metric_11;
            page = "/static/graph11.vm";
            List<Metric11> metric11List = rdb.getMetric01ByTag(tag, metricNum, System.currentTimeMillis() - TimeUnit.DAYS.toMillis(7), System.currentTimeMillis());
            data.putAll(MyGraph.getGraphDataForM11(metric11List));
        } else {
            return null;
        }
        return page;
    }

}

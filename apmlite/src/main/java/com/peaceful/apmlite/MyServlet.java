package com.peaceful.apmlite;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
        PrintWriter writer = resp.getWriter();
        String tag = req.getParameter("tag");
        String metricNumStr = req.getParameter("metric");
        List<MetricNum> metricNumList = rdb.getAllMetrics();
        List<String> tagList;
        MetricNum metricNum = MetricNum.Metric_01;
        if (StringUtils.isBlank(metricNumStr) && StringUtils.isBlank(tag)) {
            metricNum = metricNumList.get(0);
            metricNumStr = "01";
            tagList = rdb.getTagList(metricNum);
            tag = tagList.get(0);
        }


        String page = "/static/graph2.vm";
        Map<String, Object> data = Maps.newHashMap();

        if (StringUtils.isNotBlank(metricNumStr)) {
            if ("01".equalsIgnoreCase(metricNumStr)) {
                metricNum = MetricNum.Metric_01;
                List<Metric01> metric01List = rdb.getMetric01ByTag(tag, metricNum, System.currentTimeMillis() - TimeUnit.HOURS.toMillis(1), System.currentTimeMillis());
                data = MyGraph.getGraphDataForM01(metric01List);
            } else if ("11".equalsIgnoreCase(metricNumStr)) {
                metricNum = MetricNum.Metric_11;
                page = "/static/graph11.vm";
                List<Metric11> metric11List = rdb.getMetric01ByTag(tag, metricNum, System.currentTimeMillis() - TimeUnit.HOURS.toMillis(1), System.currentTimeMillis());
                data = MyGraph.getGraphDataForM11(metric11List);
            }
        }
        data.put("metrics", metricNumList);
        data.put("currentMetric", metricNum);
        data.put("tagList", rdb.getTagList(metricNum));
        data.put("currentTag", tag);
        writer.println(Response.getTemplate(page, data));
    }
}

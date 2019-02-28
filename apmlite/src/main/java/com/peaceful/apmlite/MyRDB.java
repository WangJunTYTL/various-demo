package com.peaceful.apmlite;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionManager;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wangjun on 2019-02-01.
 */
public class MyRDB {

    private MyMapper mapper;
    private SqlSessionManager sqlSessionManager;
    // 这里使用Mybatis的log作为日志门面，可对接不同的log实现，也可以改为Slf4j
    private Log log = LogFactory.getLog(MyRDB.class);

    public MyRDB(String dialect) throws IOException, SQLException {
        setUp(dialect);
    }

    void setUp(String dialect) throws IOException, SQLException {
        String config = String.format("mybatis/%s/mybatis-config.xml", dialect);
        String script = String.format("mybatis/%s/CreateDB.sql", dialect);
        Reader reader = Resources.getResourceAsReader(config);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        ScriptRunner runner = new ScriptRunner(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource().getConnection());
        runner.setAutoCommit(true);
        runner.setStopOnError(false);
        runner.setLogWriter(null);
        runner.setErrorLogWriter(null);
        runner.runScript(Resources.getResourceAsReader(script));
        runner.closeConnection();
        sqlSessionManager = SqlSessionManager.newInstance(sqlSessionFactory);
        mapper = sqlSessionManager.getMapper(MyMapper.class);
    }

    public List<String> getAllTags() {
        return mapper.selectAllTags();
    }

    public void insertTag(String tag, MetricNum metricNum) {
        Preconditions.checkArgument(StringUtils.isNotBlank(tag), "tag is empty!");
        Preconditions.checkArgument(metricNum != null);
        List<TagIndex> tagIndexList = mapper.selectTag(tag, tag.hashCode(), metricNum);
        if (tagIndexList.isEmpty()) {
            mapper.insertTag(tag, tag.hashCode(), metricNum, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        } else {
            int flag = 0;
            for (TagIndex tagIndex : tagIndexList) {
                if (flag == 0) {
                    // 出现相同的tag是非正常的情况，只保留最旧的那条数据
                    mapper.updateTagTime(tagIndex.getId());
                } else {
                    mapper.deleteTag(tagIndex.getId());
                }
                flag++;
            }
        }
    }

    public List<MetricNum> getAllMetrics() {
        return mapper.selectAllMetrics();
    }

    public List<String> getTagList(MetricNum metricNum) {
        return mapper.selectTagListByMetric(metricNum);
    }

    public <T> List<T> getMetric01ByTag(String tag, MetricNum metricNum, long startTime, long endTime) {
        List<Map<String, Object>> tList = mapper.selectMetricByTag(tag, tag.hashCode(), metricNum.num, new Timestamp(startTime), new Timestamp(endTime));

        if (metricNum == MetricNum.Metric_01 || metricNum == MetricNum.Metric_03) {
            List<T> metric01List = new ArrayList<>();
            for (Map map : tList) {
                Metric01 metric01 = new Metric01();
                metric01.toBean(map);
                metric01List.add((T) metric01);

            }
            return metric01List;
        } else if (metricNum == MetricNum.Metric_11) {
            List<T> metricList = Lists.newArrayList();
            for (Map map : tList) {
                Metric11 metric11 = new Metric11();
                metric11.toBean(map);
                metricList.add((T) metric11);
            }
            return metricList;
        }
        return null;
    }


    public void insertMetric(MetricNum metricNum, Map<String, Object> data) {
        Preconditions.checkArgument(data.containsKey("tag") && StringUtils.isNotBlank(data.get("tag").toString()), "tag is empty!");
        String tag = (String) data.get("tag");
        if (data.containsKey("metric_num")) {
            // nothing
        } else {
            data.put("metric_num", metricNum.num);
        }
        if (data.containsKey("hashcode")) {

        } else {
            data.put("hashcode", tag.hashCode());
        }
        mapper.insertMetric(data);
        insertTag(tag, metricNum);
    }


}

package com.peaceful.apmlite;

import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by wangjun on 2019-02-01.
 */
public interface MyMapper {

    List<String> selectAllTags();

    List<String> selectTagListByMetric(MetricNum metricNum);

    void insertTag(
            @Param("tag") String tag, @Param("hashcode") int hashCode,
            @Param("metric_num") MetricNum metricNum,
            @Param("createTime") Timestamp createTime, @Param("updateTime") Timestamp updateTime);

    List<TagIndex> selectTag(@Param("tag") String tag, @Param("hashcode") int hashCode,
                             @Param("metric_num") MetricNum metricNum);

    boolean deleteTag(@Param("id") long id);

    boolean updateTagTime(@Param("id") long id);

    List<MetricNum> selectAllMetrics();

    List<Map<String, Object>> selectMetricByTag(@Param("tag") String tag, @Param("hashcode") int hashcode,
                                                @Param("metric_num") int metricNum,
                                                @Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime);

    void insertMetric(Map<String, Object> data);

}

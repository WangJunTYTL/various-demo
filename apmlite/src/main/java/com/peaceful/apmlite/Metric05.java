package com.peaceful.apmlite;

import java.sql.Timestamp;

/**
 * Created by wangjun38 on 2019-02-10.
 */
public class Metric05 {

    private long id;
    private String slowSql;
    private String costTime;
    private Timestamp createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSlowSql() {
        return slowSql;
    }

    public void setSlowSql(String slowSql) {
        this.slowSql = slowSql;
    }

    public String getCostTime() {
        return costTime;
    }

    public void setCostTime(String costTime) {
        this.costTime = costTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}

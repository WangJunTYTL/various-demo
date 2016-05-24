package com.peaceful.demo.es.sqlconvert;

import com.google.common.base.Predicates;
import com.peaceful.common.util.chain.BaseChain;
import com.peaceful.common.util.chain.Chain;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;

/**
 * select sql 转 es 的 bool query
 * 支持： =、!=、in、not in、>、 >=、 <、 <=
 * 暂不支持： between and
 * <p/>
 * Created by wangjun on 16/2/17.
 */
public class ToEs {


    private static Logger logger = LoggerFactory.getLogger(ToEs.class);
    private static Chain chain = new BaseChain();

    static {
        chain.addCommand(new In());
        chain.addCommand(new Le());
        chain.addCommand(new Ge());
        chain.addCommand(new Ne());
        chain.addCommand(new Eq());
        chain.addCommand(new Other());
    }


    private static BoolQueryBuilder select(String sql) throws Exception {
        Assert.hasLength(sql, "sql is wrong!");

        String[] analyse = sql.trim().split("\\s");
        Assert.notEmpty(analyse, "{} is wrong!");
        Assert.isTrue(analyse[0].toLowerCase().equals("select"), "not is select sql");
        BoolQueryBuilder queryBuilder = boolQuery();
        // sql 解析上下文信息
        SqlContext sqlContext = new SqlContext();
        logger.debug("input sql->{}",sql);
        sql = new Format(sql).go();
        logger.debug("format sql->{}",sql);
        sqlContext.put(SqlContextConst.SQL, sql);
        sqlContext.put(SqlContextConst.WORLDS, analyse);
        sqlContext.put(SqlContextConst.TABLE, analyse[3]);
        sqlContext.put(SqlContextConst.POINTER, 4);
        sqlContext.put(SqlContextConst.CURRENT_WOLD, analyse[4]);
        sqlContext.put(SqlContextConst.BOOLQUERY, queryBuilder);
        logger.debug("table: {}", sqlContext.get(SqlContextConst.TABLE));
        // 解析
        while ((Integer) sqlContext.get(SqlContextConst.POINTER) < analyse.length) {
            chain.execute(sqlContext);
        }
        logger.debug("sql query->{}", sql);
        logger.debug("es bool query->{}", queryBuilder.toString());
        return queryBuilder;
    }

    public static BoolQueryBuilder convert(String sql) throws Exception {
        return select(sql);
    }


    // only for test
    public static void main(String[] args) throws Exception {
        // =  in
//        String sql = "SELECT phone from t_user_20160128 where 1=1  and city_level not in ( 4,5,6,7,8,9) and is_vip=0 and is_installed=1";
//        String sql = "SELECT count(1) from t_user_20151105 where 1=1  and order_num_15>=2";
        // <
//        String sql = "SELECT phone from t_user_20151120 where id>0  and city_id in (3) and min_expire_date=1 and day_bonus_rate_30>30 and comment_times_7<7 and envelope_day>7";
        String sql = "SELECT phone from t_user_20160316 where 1=1  and city_id in (2)";

        convert(sql);

    }
}

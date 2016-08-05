/**
 * 用于sql查询可以解释为elasticsearch的bool查询
 * <p/>
 * 支持sql：>、<、 >=、<=、!=
 * <p/>
 * 基本解释流程：
 * 1. 格式化sql
 * 2. 遍历扫描sql的关键字，比如 遇到 > 则加入range查询
 * 3. 返回BoolQueryBuilder
 * <p/>
 * Created by wangjun on 16/3/14.
 */
package com.peaceful.demo.es.sqlconvert;
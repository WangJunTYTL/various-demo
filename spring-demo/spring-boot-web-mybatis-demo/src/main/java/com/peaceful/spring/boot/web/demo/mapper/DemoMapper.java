package com.peaceful.spring.boot.web.demo.mapper;

import org.apache.ibatis.annotations.Select;

/**
 * Created by wangjun38 on 2018/5/4.
 */
public interface DemoMapper {

    @Select("select count(1) from user")
    int selectCount();
}

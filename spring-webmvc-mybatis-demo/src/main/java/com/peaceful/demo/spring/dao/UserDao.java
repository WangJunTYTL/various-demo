package com.peaceful.demo.spring.dao;

import com.peaceful.demo.spring.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by wangjun on 14/12/25.
 */
public interface UserDao {

    @Select("SELECT * FROM user WHERE id = #{userId}")
    User getUser(@Param("userId") long userId);
}

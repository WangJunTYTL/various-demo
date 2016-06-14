package com.peaceful.demo.spring.dao;

import com.peaceful.demo.spring.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by wangjun on 14/12/25.
 */
public interface UserDao {

    @Select("SELECT * FROM user WHERE id = #{userId}")
    User getUser(@Param("userId") long userId);

    @Insert("Insert into user (`name`,`isdel`) values (#{name},0)")
    int insertUser(User user);

    @Select("select * from user where name like concat('%',#{nameLike},'%') ")
    List<User> queryUsersByName(String nameLike);
}

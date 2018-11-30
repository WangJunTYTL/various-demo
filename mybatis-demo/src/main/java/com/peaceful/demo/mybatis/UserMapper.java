package com.peaceful.demo.mybatis;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by wangjun38 on 2018/5/19.
 */
public interface UserMapper {

    User selectById(int id);

    @Update("update user set name = #{name} where id = #{id}")
    long update(@Param("id") int id, @Param("name") String name);

    @Select("select * from `user` where name = #{name}")
    List<User> selectByName(String name);

    @Select("select * from `user` where name = #{name} and id = #{id}")
    List<User> selectByIdAndName(@Param("name") String name, @Param("id") int id);
}

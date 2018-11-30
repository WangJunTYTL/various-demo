package com.peaceful.demo.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by wangjun38 on 2018/5/19.
 */
public class MybatisMain {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.selectById(1);
        System.out.println(user.getName());
//        session.close(); // 如果关闭，下面就会报错了
        List<User> userList = userMapper.selectByName("test");
        System.out.println(userList.size());
        session.clearCache();
        List data = session.selectList("com.peaceful.demo.mybatis.UserMapper.selectById", 1);
        System.out.println(data.size());

        userMapper.selectById(2);
        session.clearCache();

        userMapper.selectByIdAndName("test",1);
    }
}

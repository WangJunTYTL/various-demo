package com.peaceful.demo.mybatis;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by wangjun38 on 2018/5/20.
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Transactional
    public void selectById(int id) {
        userMapper.selectById(id);

//        userMapper.update(id,String.valueOf(System.currentTimeMillis()));
        userMapper.selectByName("test");
//        throw new RuntimeException("intercept");

    }
}

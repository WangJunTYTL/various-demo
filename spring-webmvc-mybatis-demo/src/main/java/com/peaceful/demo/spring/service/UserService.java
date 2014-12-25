package com.peaceful.demo.spring.service;

import com.peaceful.demo.spring.dao.UserDao;
import com.peaceful.demo.spring.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wangjun on 14/12/25.
 */
@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUserById(long id) {
        return userDao.getUser(id);
    }
}

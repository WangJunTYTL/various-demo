package com.peaceful.demo.rose.service;

import com.peaceful.demo.rose.dao.UserDAO;
import com.peaceful.demo.rose.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangjun on 14/12/9.
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public User getUser(){
        return userDAO.getUser();
    }

}

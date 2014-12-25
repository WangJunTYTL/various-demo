package com.peaceful.demo.spring.dao;

import com.peaceful.common.util.Util;
import com.peaceful.demo.spring.domain.User;
import com.peaceful.demo.spring.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class UserDaoTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetUser() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath*:/spring/applicationContext*.xml");
        UserDao userDao = applicationContext.getBean(UserDao.class);
        User user = userDao.getUser(165);
        if (user != null)
            Util.report(user.name);
    }
}
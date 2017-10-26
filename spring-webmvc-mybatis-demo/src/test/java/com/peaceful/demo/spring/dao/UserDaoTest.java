package com.peaceful.demo.spring.dao;

import com.peaceful.common.util.Util;
import com.peaceful.demo.spring.domain.User;
import com.peaceful.demo.spring.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class UserDaoTest {

    ClassPathXmlApplicationContext applicationContext;
    UserDao userdao;

    @Before
    public void setUp() throws Exception {
         applicationContext = new ClassPathXmlApplicationContext(
                "classpath*:/spring/applicationContext*.xml");
         userdao = applicationContext.getBean(UserDao.class);
    }

    @Test
    public void testGetUser() throws Exception {
        User user = userdao.getUser(165);
        if (user != null)
            Util.report(user.name);
    }

    @Test
    public void insertUser() throws Exception {
        User user = new User();
        user.setName("WJ");
        userdao.insertUser(user);
    }
}
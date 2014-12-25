package com.peaceful.demo.spring.service;

import com.peaceful.common.util.Util;
import com.peaceful.demo.spring.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserService userService;

    @Before
    public void setUp() throws Exception {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath*:/spring/applicationContext*.xml");
        userService = applicationContext.getBean(UserService.class);

    }

    @Test
    public void testGetUserById() throws Exception {
        User user = userService.getUserById(165);
        if (user != null)
            Util.report(user.name);
    }
}
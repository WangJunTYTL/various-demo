package com.wj.hibernate.ServiceImpl;

import com.wj.hibernate.Service.SystemService;
import com.wj.hibernate.Service.UserService;
import com.wj.hibernate.domain.DJSystem;
import com.wj.hibernate.domain.DJUser;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import util.DomainUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wangjun on 14-4-17.
 */
public class SystemServiceImplTest {

    SystemService systemService = null;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @org.junit.Before
    public void setUp() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        systemService = applicationContext.getBean("systemService", SystemService.class);
    }

    @Test
    public void insertSystem() {
        DJSystem system = DomainUtil.getSystem();
        systemService.insertSystem(system);
    }

    @Test
    public void updateSystem() {
        DJSystem system = new DJSystem();
        system.id=1;
        system.description="desc";
        system.operator="mon";
        system.name="systenName";
        DJUser user = new DJUser();
        user.email="emial";
        Set<DJUser> users= new HashSet<DJUser>();
        users.add(user);
        system.users=users;
        systemService.updateSystem(system);
    }

}

package com.wj.hibernate.ServiceImpl;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.wj.hibernate.Service.RoleService;
import com.wj.hibernate.Service.UserService;
import com.wj.hibernate.domain.DJRole;
import com.wj.hibernate.domain.DJSystem;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import util.DomainUtil;

import java.util.List;

/**
 * Created by wangjun on 14-4-17.
 */
public class RoleServiceImplTest {

    RoleService roleService = null;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @org.junit.Before
    public void setUp() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        roleService = applicationContext.getBean("roleService", RoleService.class);
    }
    @Test
    public void testFindAllRoleSortBySystem() throws Exception {

    }

    @Test
    public void testFindRoleByRoleId() throws Exception {

    }

    @Test
    public void testInsertRole() throws Exception {
        DJRole role =DomainUtil.getRole();
        DJSystem system= DomainUtil.getSystem();
        system.id=1;
        role.system= system;
        roleService.insertRole(role);
    }

    @Test
    public void testUpdateRole() throws Exception {

    }

    @Test
    public void testDeleteRole() throws Exception {

    }
}

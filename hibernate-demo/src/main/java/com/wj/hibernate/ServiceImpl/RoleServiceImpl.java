package com.wj.hibernate.ServiceImpl;

import com.wj.hibernate.Service.RoleService;
import com.wj.hibernate.dao.RoleDao;
import com.wj.hibernate.dao.SystemDao;
import com.wj.hibernate.domain.DJRole;
import com.wj.hibernate.domain.DJSystem;
import com.wj.hibernate.util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangjun on 14-4-17.
 */
@Component(value = "roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    SystemDao systemDao = null;
    @Autowired
    RoleDao roleDao = null;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void setSystemDao(SystemDao systemDao) {
        this.systemDao = systemDao;
    }


    public DJRole findRoleByRoleId(Integer id) {
        return roleDao.findRoleById(id);
    }


    public void insertRole(DJRole role) {
        roleDao.inserte(role);

    }

    public void updateRole(DJRole role) {
        roleDao.update(role);
    }

}

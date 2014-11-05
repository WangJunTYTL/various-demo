package com.wj.hibernate.ServiceImpl;

import com.wj.hibernate.Service.SystemService;
import com.wj.hibernate.dao.SystemDao;
import com.wj.hibernate.domain.*;
import com.wj.hibernate.util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangjun on 14-4-17.
 */
@Component(value = "systemService")
public class SystemServiceImpl implements SystemService {

    @Autowired
    SystemDao systemDao = null;

    public void setSystemDao(SystemDao systemDao) {
        this.systemDao = systemDao;
    }

    public List<DJSystem> findRolesSortBySystem() {
        List<DJSystem> djSystems = systemDao.findAllSystems();
        HibernateUtil.systemLoad(djSystems, HibernateUtil.ROLE);
        return djSystems;
    }

    public List<DJSystem> findUsersSortBySystem() {
        List<DJSystem> djSystems = systemDao.findAllSystems();
        HibernateUtil.systemLoad(djSystems, HibernateUtil.USER);
        return djSystems;
    }

    public List<DJSystem> findResourcesSortBySystem() {

        List<DJSystem> djSystems = systemDao.findAllSystems();
        HibernateUtil.systemLoad(djSystems, HibernateUtil.RESOURCE);
        return djSystems;
    }

    public List<DJSystem> findMenusSortBySystem() {

        List<DJSystem> djSystems = systemDao.findAllSystems();
        HibernateUtil.systemLoad(djSystems, HibernateUtil.MENU);
        return djSystems;
    }

    public void insertSystem(DJSystem system) {
        systemDao.inserte(system);
    }

    public DJSystem findSystemBySystemId(Integer systemId) {
        return systemDao.findSystemById(systemId);
    }

    public void updateSystem(DJSystem system) {
        systemDao.update(system);
    }


}

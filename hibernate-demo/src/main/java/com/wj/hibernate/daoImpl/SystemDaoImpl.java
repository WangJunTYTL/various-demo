package com.wj.hibernate.daoImpl;

import com.wj.hibernate.dao.SystemDao;
import com.wj.hibernate.domain.DJResource;
import com.wj.hibernate.domain.DJSystem;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Created by wangjun on 14-4-15.
 */
@Component(value = "systemDao")
public class SystemDaoImpl implements SystemDao {
    @Autowired
    @Qualifier("sessionFactory")
    SessionFactory sessionFactory = null;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SystemDaoImpl() {

    }


    public List findAllSystems() {
        return sessionFactory.getCurrentSession().createQuery("from DJSystem ").list();
    }

    public DJSystem findSystemById(Integer id) {
        return (DJSystem) sessionFactory.getCurrentSession().get(DJSystem.class,id);
    }

    public void inserte(DJSystem system) {

        sessionFactory.getCurrentSession().save(system);

    }

    public void update(DJSystem system) {
        sessionFactory.getCurrentSession().update(system);
    }

    public void delate(DJSystem system) {
        sessionFactory.getCurrentSession().delete(system);
    }
}

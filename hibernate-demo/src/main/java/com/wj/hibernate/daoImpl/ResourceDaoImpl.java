package com.wj.hibernate.daoImpl;

import com.wj.hibernate.dao.ResourceDao;
import com.wj.hibernate.domain.DJResource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by wangjun on 14-4-17.
 */
@Component(value = "resourceDao")
public class ResourceDaoImpl implements ResourceDao {
    @Autowired
    @Qualifier("sessionFactory")
    SessionFactory sessionFactory = null;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public DJResource findResourceByMenuId(Integer id) {
        return (DJResource) sessionFactory.getCurrentSession().get(DJResource.class, id);
    }

    public void inserte(DJResource resource) {
        sessionFactory.getCurrentSession().save(resource);
    }

    public void update(DJResource resource) {
        sessionFactory.getCurrentSession().update(resource);
    }

    public void delate(DJResource resource) {
        sessionFactory.getCurrentSession().delete(resource);
    }
}

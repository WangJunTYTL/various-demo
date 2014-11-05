package com.wj.hibernate.daoImpl;

import com.wj.hibernate.dao.MenuDao;
import com.wj.hibernate.domain.DJMenu;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by wangjun on 14-4-17.
 */
@Component(value = "menuDao")
public class MenuDaoImpl implements MenuDao {

    @Autowired
    @Qualifier("sessionFactory")
    SessionFactory sessionFactory = null;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public DJMenu findMenuByMenuId(Integer id) {
        return (DJMenu) sessionFactory.getCurrentSession().get(DJMenu.class, id);
    }

    public void inserte(DJMenu djMenu) {
        sessionFactory.getCurrentSession().save(djMenu);
    }

    public void update(DJMenu djMenu) {
        sessionFactory.getCurrentSession().update(djMenu);
    }

    public void delate(DJMenu djMenu) {
        sessionFactory.getCurrentSession().delete(djMenu);
    }
}

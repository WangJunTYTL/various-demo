package com.wj.hibernate.daoImpl;

import com.wj.hibernate.dao.RoleDao;
import com.wj.hibernate.domain.DJRole;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangjun on 14-4-17.
 */
@Component(value = "roleDao")
public class RoleDaoImpl implements RoleDao {
    @Autowired
    @Qualifier("sessionFactory")
    SessionFactory sessionFactory = null;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<DJRole> findAllRoleSortBySystem() {
        return sessionFactory.getCurrentSession().createQuery("from role").list();
    }

    public DJRole findRoleById(Integer id) {
        return (DJRole) sessionFactory.getCurrentSession().get(DJRole.class,id);
    }

    public void inserte(DJRole djJRole) {
        sessionFactory.getCurrentSession().save(djJRole);
    }

    public void update(DJRole djJRole) {
        sessionFactory.getCurrentSession().update(djJRole);
    }

    public void delate(DJRole djJRole) {
        sessionFactory.getCurrentSession().delete(djJRole);
    }
}

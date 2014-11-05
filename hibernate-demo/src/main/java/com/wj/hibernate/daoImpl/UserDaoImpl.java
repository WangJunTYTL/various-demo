package com.wj.hibernate.daoImpl;

import com.wj.hibernate.dao.UserDao;
import com.wj.hibernate.domain.DJMenu;
import com.wj.hibernate.domain.DJResource;
import com.wj.hibernate.domain.DJRole;
import com.wj.hibernate.domain.DJUser;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;


/**
 * Created by wangjun on 14-4-15.
 */
@Component(value = "userDao")
public class UserDaoImpl implements UserDao {
    @Autowired
    @Qualifier("sessionFactory")
    SessionFactory sessionFactory = null;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserDaoImpl() {

    }




    public DJUser findUserByUserId(Integer id) {
       return (DJUser) sessionFactory.getCurrentSession().get(DJUser.class,id);
    }

    public List<DJResource> findCanAccessResourcesOfUserOfSystem(Integer uid, Integer systemId) {
        Set<DJResource> result = new HashSet<DJResource>();
         DJUser user = findUserByUserId(uid);
        Collection<DJRole> liveRoles  = sessionFactory.getCurrentSession().createFilter(
                user.roles,
                ("where this.isdel = 1 and this.system.id = ?")
        ).setInteger(0,systemId).list();

        for (DJRole role:liveRoles){
            Collection<DJResource> resources  = sessionFactory.getCurrentSession().createFilter(
                    role.resources,
                    ("where this.isdel = 1")
            ).list();
            for (DJResource resource:resources){
                result.add(resource);
            }
        }
       List<DJResource> resourceList = new ArrayList<DJResource>();
        for (DJResource resource:result){
            resourceList.add(resource);
        }
        return resourceList;
    }

    public List<DJMenu> findCanAccessMenusOfUserOfSystem(Integer id, Integer systemId) {
        Set<DJMenu> result = new HashSet<DJMenu>();
        DJUser user = findUserByUserId(id);
        Collection<DJRole> liveRoles  = sessionFactory.getCurrentSession().createFilter(
                user.roles,
                ("where this.isdel = 1 and this.system.id = ?")
        ).setInteger(0,systemId).list();

        for (DJRole role:liveRoles){
            Collection<DJMenu> resources  = sessionFactory.getCurrentSession().createFilter(
                    role.menus,
                    ("where this.isdel = 1")
            ).list();
            for (DJMenu menu:resources){
                result.add(menu);
            }
        }
        List<DJMenu> menuList = new ArrayList<DJMenu>();
        for (DJMenu menu:result){
            menuList.add(menu);
        }
        return menuList;
    }

    public List<DJRole> findNowRolesOfUserOfSystem(Integer id, Integer systemId) {
        DJUser user = findUserByUserId(id);
        Collection<DJRole> liveRoles  = sessionFactory.getCurrentSession().createFilter(
                user.roles,
                ("where this.isdel = 1 and this.system.id = ?")
        ).setInteger(0,systemId).list();
        return (List<DJRole>) liveRoles;
    }

    public void insert(DJUser user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public void update(DJUser user) {
        sessionFactory.getCurrentSession().update(user);
    }

}

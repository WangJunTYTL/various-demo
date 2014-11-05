package com.wj.hibernate.ServiceImpl;

import com.wj.hibernate.Service.UserService;
import com.wj.hibernate.dao.SystemDao;
import com.wj.hibernate.dao.UserDao;
import com.wj.hibernate.domain.DJMenu;
import com.wj.hibernate.domain.DJResource;
import com.wj.hibernate.domain.DJRole;
import com.wj.hibernate.domain.DJUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by wangjun on 14-4-17.
 */
@Component(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    SystemDao systemDao = null;
    @Autowired
    UserDao userDao = null;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setSystemDao(SystemDao systemDao) {
        this.systemDao = systemDao;
    }


    public java.util.List<DJResource> findCanAccressResourcesOfCerrentSystemAndUser(Integer userId, Integer systemId) {
        return null;
    }

    public java.util.List<DJMenu> findCanAccressMenusOfCerrentSystemAndUser(Integer userId, Integer systemId) {
        return null;
    }

    public java.util.List<DJRole> findHasAuthOfUser(Integer userId, Integer systemId) {
        return null;
    }

    public DJUser findUserByUserId(Integer userId) {
        return userDao.findUserByUserId(userId);
    }

    public boolean findUserIsDel(Integer userId, Integer systemId) {
        return false;
    }

    public void insertUser(DJUser user) {
        userDao.insert(user);
    }

    public void updateUser(DJUser user) {
        userDao.update(user);
    }


}


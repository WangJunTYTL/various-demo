package com.wj.hibernate.dao;


import com.wj.hibernate.domain.DJMenu;
import com.wj.hibernate.domain.DJResource;
import com.wj.hibernate.domain.DJRole;
import com.wj.hibernate.domain.DJUser;

import java.util.List;

/**
 * Created by wangjun on 14-4-15.
 */
public interface UserDao {

    abstract DJUser findUserByUserId(Integer id);
    List<DJResource> findCanAccessResourcesOfUserOfSystem(Integer id, Integer systemId);
    List<DJMenu> findCanAccessMenusOfUserOfSystem(Integer id, Integer systemId);
    List<DJRole> findNowRolesOfUserOfSystem(Integer id, Integer systemId);
    abstract void insert(DJUser user);
    abstract void update(DJUser user);

}

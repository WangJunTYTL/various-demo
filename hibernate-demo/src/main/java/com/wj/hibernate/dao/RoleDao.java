package com.wj.hibernate.dao;


import com.wj.hibernate.domain.DJRole;

/**
 * Created by wangjun on 14-4-15.
 */
public interface RoleDao {

    abstract DJRole findRoleById(Integer id);

    abstract void inserte(DJRole djJRole);

    abstract void update(DJRole djJRole);

    abstract void delate(DJRole djJRole);

}

package com.wj.hibernate.Service;

import com.wj.hibernate.domain.DJRole;

/**
 * Created by wangjun on 14-4-17.
 */
public interface RoleService {


    void insertRole(DJRole role);

    DJRole findRoleByRoleId(Integer roleId);

    void updateRole(DJRole role);

}

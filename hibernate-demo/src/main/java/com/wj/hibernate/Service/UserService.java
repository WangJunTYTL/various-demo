package com.wj.hibernate.Service;

import com.wj.hibernate.domain.DJMenu;
import com.wj.hibernate.domain.DJResource;
import com.wj.hibernate.domain.DJRole;
import com.wj.hibernate.domain.DJUser;

import java.util.List;

/**
 * Created by wangjun on 14-4-17.
 */
public interface UserService {

    List<DJResource> findCanAccressResourcesOfCerrentSystemAndUser(Integer userId, Integer systemId);

    List<DJMenu> findCanAccressMenusOfCerrentSystemAndUser(Integer userId, Integer systemId);

    List<DJRole> findHasAuthOfUser(Integer userId, Integer systemId);

    DJUser findUserByUserId(Integer userId);

    boolean findUserIsDel(Integer userId, Integer systemId);
    /**
     * 每个用户可以在多个系统下注册，如果用户在该系统下已注册，不允许重复注册
     * @param user
     */
    void insertUser(DJUser user);

    /**
     * 注意删除用户的方法是直接将isdel改为0
     * @param user
     */
    void updateUser(DJUser user);

}

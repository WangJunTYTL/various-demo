package com.wj.hibernate.dao;


import com.wj.hibernate.domain.DJResource;

/**
 * Created by wangjun on 14-4-15.
 */
public interface ResourceDao {

    DJResource findResourceByMenuId(Integer id);

    abstract void inserte(DJResource resource);

    abstract void update(DJResource resource);

    abstract void delate(DJResource resource);

}

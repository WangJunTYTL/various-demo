package com.wj.hibernate.dao;


import com.wj.hibernate.domain.DJMenu;

/**
 * Created by wangjun on 14-4-15.
 */
public interface MenuDao {

    DJMenu findMenuByMenuId(Integer id);

    abstract void inserte(DJMenu djMenu);

    abstract void update(DJMenu djMenu);

    abstract void delate(DJMenu djMenu);

}

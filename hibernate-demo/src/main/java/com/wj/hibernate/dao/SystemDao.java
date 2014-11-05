package com.wj.hibernate.dao;

import com.wj.hibernate.domain.DJResource;
import com.wj.hibernate.domain.DJSystem;
import com.wj.hibernate.domain.DJUser;

import java.util.List;


/**
 * Created by wangjun on 14-4-15.
 */
public interface SystemDao {
    abstract List<DJSystem> findAllSystems();
    abstract DJSystem findSystemById(Integer id);
    abstract void inserte(DJSystem system);
    abstract void update(DJSystem system);
    abstract void delate(DJSystem system);

}

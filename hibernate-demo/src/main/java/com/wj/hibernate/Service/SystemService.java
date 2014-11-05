package com.wj.hibernate.Service;

import com.wj.hibernate.domain.*;

import java.util.List;

/**
 * Created by wangjun on 14-4-17.
 */
public interface SystemService {
    List<DJSystem> findRolesSortBySystem();
    List<DJSystem> findUsersSortBySystem();
    List<DJSystem> findResourcesSortBySystem();
    List<DJSystem> findMenusSortBySystem();
    void insertSystem(DJSystem system);
    DJSystem findSystemBySystemId(Integer systemId);
    void updateSystem(DJSystem system);
}

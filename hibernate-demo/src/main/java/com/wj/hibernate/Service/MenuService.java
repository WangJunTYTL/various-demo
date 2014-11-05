package com.wj.hibernate.Service;

import com.wj.hibernate.domain.DJMenu;

/**
 * Created by wangjun on 14-4-17.
 */
public interface MenuService {
    void insertMenu(DJMenu menu);

    DJMenu findMenuByMenuId(Integer menuId);

    void updateMenu(DJMenu menu);
}

package com.wj.hibernate.ServiceImpl;

import com.wj.hibernate.Service.MenuService;
import com.wj.hibernate.dao.MenuDao;
import com.wj.hibernate.dao.SystemDao;
import com.wj.hibernate.domain.DJMenu;
import com.wj.hibernate.domain.DJSystem;
import com.wj.hibernate.util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangjun on 14-4-17.
 */
@Component(value = "menuService")
public class MenuServiceImpl implements MenuService {
    @Autowired
    SystemDao systemDao = null;

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Autowired
    MenuDao menuDao = null;

    public void setSystemDao(SystemDao systemDao) {
        this.systemDao = systemDao;
    }


    public DJMenu findMenuByMenuId(Integer id) {
        return menuDao.findMenuByMenuId(id);
    }

    public void insertMenu(DJMenu menu) {
        menuDao.inserte(menu);
    }

    public void updateMenu(DJMenu menu) {
        menuDao.update(menu);
    }

}

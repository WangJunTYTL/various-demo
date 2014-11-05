package com.wj.hibernate.ServiceImpl;

import com.wj.hibernate.Service.ResourceService;
import com.wj.hibernate.dao.ResourceDao;
import com.wj.hibernate.dao.SystemDao;
import com.wj.hibernate.domain.DJResource;
import com.wj.hibernate.domain.DJSystem;
import com.wj.hibernate.util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangjun on 14-4-17.
 */
@Component(value = "resourceService")
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    SystemDao systemDao = null;
    @Autowired
    ResourceDao resourceDao = null;

    public void setSystemDao(SystemDao systemDao) {
        this.systemDao = systemDao;
    }

    public void setResourceDao(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
    }


    public DJResource findResourceByResourceId(Integer id) {
        return resourceDao.findResourceByMenuId(id);
    }

    public void insertResource(DJResource resource) {
        resourceDao.inserte(resource);
    }

    public void updateResource(DJResource resource) {
        resourceDao.update(resource);
    }

}

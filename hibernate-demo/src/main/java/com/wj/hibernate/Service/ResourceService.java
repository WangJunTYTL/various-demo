package com.wj.hibernate.Service;

import com.wj.hibernate.domain.DJResource;

/**
 * Created by wangjun on 14-4-17.
 */
public interface ResourceService {
    void insertResource(DJResource resource);
    DJResource findResourceByResourceId(Integer resourceId);
    void updateResource(DJResource resource);

}

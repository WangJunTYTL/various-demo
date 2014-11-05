package com.wj.hibernate.util;

import com.wj.hibernate.domain.DJSystem;

import java.util.List;

/**
 * Created by wangjun on 14-4-17.
 */
public class HibernateUtil {
    public static final int USER = 0;
    public static final int MENU = 1;
    public static final int RESOURCE = 2;
    public static final int ROLE = 3;
    public static final int ALL = 4;

    public static void systemLoad(List<DJSystem> systems, int... types) {
        for (DJSystem system : systems) {
            for (int i : types) {
                switch (i) {
                    case 0: {
                        system.users.size();
                        break;
                    }
                    case 1: {
                        system.menus.size();
                        break;
                    }
                    case 2: {
                        system.resources.size();
                        break;
                    }
                    case 3: {
                        system.roles.size();
                        break;
                    }
                    case 4: {
                        system.roles.size();
                        system.users.size();
                        system.menus.size();
                        system.resources.size();

                        break;
                    }
                }
            }
        }
    }
}

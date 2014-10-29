package com.peaceful.web.util;

import com.peaceful.util.AppConfigs;
import com.peaceful.util.impl.AppConfigsImpl;

/**
 * Date 14/10/28.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public class Global {

    private static AppConfigs appConfigs = AppConfigsImpl.getMyAppConfigs("application.properties");

    public static boolean isDev() {
        String mode = appConfigs.getString("dev.mode");
        if (mode.equals("dev"))
            return true;
        else
            return false;
    }

    public static boolean isTest() {
        String mode = appConfigs.getString("dev.mode");
        if (mode.equals("test"))
            return true;
        else
            return false;
    }

    public static boolean isProduct() {
        String mode = appConfigs.getString("dev.mode");
        if (mode.equals("product"))
            return true;
        else
            return false;
    }

    public static String getBuildVersion(){
        return appConfigs.getString("build.version");
    }
}

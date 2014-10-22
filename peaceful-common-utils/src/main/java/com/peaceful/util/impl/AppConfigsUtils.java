package com.peaceful.util.impl;

import com.peaceful.util.exception.LoadPropertiesException;
import com.peaceful.util.AppConfigs;

import java.util.Map;

/**
 * 读取properties配置文件工具类,
 * 实现接口@see MyAppConfigs
 * Created by wangjun on 14-8-18.
 */
public class AppConfigsUtils extends AbstractConfiguration {
    private  Map configMap = null;

    private AppConfigsUtils() {
    }

    public static AppConfigs getMyAppConfigs(String propertiesPath) {
        try {
            AppConfigsUtils appConfigsUtils = new AppConfigsUtils();
            appConfigsUtils.configMap = LoadProperties.loadPropertiesFromClassPath(propertiesPath);
            return appConfigsUtils;
        } catch (LoadPropertiesException e) {
            throw new LoadPropertiesException("未成功载入配置文件".concat(propertiesPath), e);
        }
    }

    @Override
    protected Map<String, String> getConfigData() {
        return configMap;
    }


    @Override
    protected String getStringFromStore(String key) {
        return getConfigData().get(key);
    }


}
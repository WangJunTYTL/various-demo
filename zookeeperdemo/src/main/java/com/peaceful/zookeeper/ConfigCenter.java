package com.peaceful.zookeeper;

import com.peaceful.util.AppConfigs;
import com.peaceful.util.exception.LoadPropertiesException;
import com.peaceful.util.impl.AbstractConfiguration;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.Map;

/**
 * Date 14/11/11.
 * Author WangJun
 * Email wangjuntytl@163.com
 *
 * 利用zookeeper实现配置中心服务
 */
public class ConfigCenter extends AbstractConfiguration {

    private Map configMap = null;

    private ConfigCenter() {
    }

    public static AppConfigs getMyAppConfigs(String path) {
        ZooKeeper zooKeeper = null;
        try {
            Stat stat = zooKeeper.exists(path, false);
            if (stat == null) {
                throw new LoadPropertiesException("未发现配置文件".concat(path));
            }
            byte[] data = zooKeeper.getData(path,false,stat);
            ConfigCenter configCenter = new ConfigCenter();
            return configCenter;
        } catch (Exception e) {
            throw new LoadPropertiesException("未成功载入配置文件".concat(path), e);
        }
    }

    protected Map<String, String> getConfigData() {
        return this.configMap;
    }

    protected String getStringFromStore(String key) {
        return this.getConfigData().get(key);
    }


}

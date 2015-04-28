/**
 *
 */
package com.peaceful.redis.deploy.share;

import com.peaceful.common.util.AppConfigs;
import com.peaceful.common.util.impl.AppConfigsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 集群配置中心
 * <p/>
 * Created by wangjun on 15/1/22.
 */
public class JedisClusterConfigCenter {

    static Logger logger = LoggerFactory.getLogger(JedisClusterConfigCenter.class);
    private static final ConcurrentMap<String, JedisClusterConfigNode> configByName = new ConcurrentHashMap<String, JedisClusterConfigNode>();

    //app启动时需要读取的集群配置文件
    static {
        //        appendClusterConfig("default", "redis/redis-default-cluster.properties");
        appendClusterConfig("redis/redis-local-cluster.properties");

    }

    private JedisClusterConfigCenter() {
    }


    public static JedisClusterConfigNode getClusterConfig(String name) {
        return configByName.get(name);
    }


    public static void appendClusterConfig(String... configPaths) {
        for (String path : configPaths) {
            appendClusterConfig(path);
        }
    }

    /**
     * 读取redis集群配置文件
     *
     * @param configPath
     */
    public static void appendClusterConfig(String configPath) {
        JedisClusterConfigNode jedisClusterConfigNode = new JedisClusterConfigNode();
        AppConfigs appConfigs = AppConfigsImpl.getMyAppConfigs(configPath);
        Map clusterConfig = appConfigs.toMap();
        Set<String> keys = clusterConfig.keySet();
        Set<String> hosts = new HashSet<String>();
        List<HostAndPort> jedisNodeConfigList = new ArrayList<HostAndPort>();
        String clusterName = null;
        try {
            for (String key : keys) {
                String[] nameSpace = key.split("\\.");
                hosts.add(nameSpace[1]);
                clusterName = nameSpace[0];
            }
            for (String host : hosts) {
                String keyPrefix = clusterName.concat(host);
                String host_ = appConfigs.getString(keyPrefix.concat(".host"));
                int port = appConfigs.getInt(keyPrefix.concat(".host.port"));
                jedisNodeConfigList.add(new HostAndPort(host_, port));
            }
        } catch (Exception e) {
            throw new RuntimeException("redis cluster config  file " + configPath + " format error");
        }
        jedisClusterConfigNode.setClusterName(clusterName);
        jedisClusterConfigNode.setClusterConfigFile(configPath);
        jedisClusterConfigNode.setNodes(jedisNodeConfigList);
        configByName.put(clusterName, jedisClusterConfigNode);
        logger.info("current redis cluster config is :{}", configByName);
    }
}

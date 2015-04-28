/**
 *
 */
package com.peaceful.redis.deploy.share;

import redis.clients.jedis.HostAndPort;

import java.util.List;

/**
 * jedis cluster config
 * <p/>
 * Created by wangjun on 15/1/22.
 */
public class JedisClusterConfigNode {

    private String clusterName;

    private String clusterConfigFile;

    public void setClusterConfigFile(String clusterConfigFile) {
        this.clusterConfigFile = clusterConfigFile;
    }

    private List<HostAndPort> nodes;

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public List<HostAndPort> getNodes() {
        return nodes;
    }

    public void setNodes(List<HostAndPort> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return "JedisClusterConfigNode [clusterName=" + clusterName + ", nodes=" + nodes + "]";
    }

}

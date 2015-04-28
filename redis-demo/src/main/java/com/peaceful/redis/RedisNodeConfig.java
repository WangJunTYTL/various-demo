package com.peaceful.redis;

/**
 * Date 14/10/29.
 * Author WangJun
 * Email wangjuntytl@163.com
 *
 * <h2>redis 节点配置，一个redis服务（一台机器的某个端口）算是一个节点服务</h2>
 */
public class RedisNodeConfig {

    private String host;

    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "RedisNodeConfig [host=" + host + ", port=" + port + "]";
    }
}

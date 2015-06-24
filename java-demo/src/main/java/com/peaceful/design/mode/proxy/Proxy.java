package com.peaceful.design.mode.proxy;

/**
 * Date 14/10/20.
 * Author WangJun
 * Email wangjuntytl@163.com
 * <p/>
 * <p/>
 * <h3>代理模式：</h3>
 * <ol>
 * <li>增强被代替者功能</li>
 * <li>延迟加载</li>
 * </ol>
 */
public class Proxy {

    public static void main(String[] args) {
        IDB idb = new DBProxy();
        //idb.getDataSoure();
    }

}

interface IDB {

    String getDataSoure();

}

class DB implements IDB {

    @Override
    public String getDataSoure() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "this is a dataSource";
    }
}

class DBProxy implements IDB {

    private IDB idb = null;

    @Override
    public String getDataSoure() {
        if (idb == null)
            return new DB().getDataSoure();
        return null;
    }
}

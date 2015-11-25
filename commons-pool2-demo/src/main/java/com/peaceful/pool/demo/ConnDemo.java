package com.peaceful.pool.demo;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/10/28
 * @since 1.6
 */

public class ConnDemo {

    public static void main(String[] args) throws Exception {
        ConnPoolConfig connPoolConfig = new ConnPoolConfig();
        connPoolConfig.setMinIdle(5);
        connPoolConfig.setMaxIdle(8);
        ConnPool connPool = new ConnPool(connPoolConfig);
        Conn conn1 = connPool.borrowObject();
        Conn conn2 = connPool.borrowObject();
        Conn conn3 = connPool.borrowObject();
        Conn conn4 = connPool.borrowObject();
        Conn conn5 = connPool.borrowObject();
        conn1.report();
        connPool.returnObject(conn1);
        conn2.report();
        connPool.returnObject(conn2);
        conn3.report();
        connPool.returnObject(conn3);
        conn4.report();
        connPool.returnObject(conn4);
        conn5.report();
        connPool.returnObject(conn5);

        conn5.report();

        // 被归还的对象的引用，不可以在次归还
        // java.lang.IllegalStateException: Object has already been retured to this pool or is invalid
        try {
            connPool.returnObject(conn5);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

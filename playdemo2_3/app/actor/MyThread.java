package actor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangjun on 14-7-9.
 */
public class MyThread extends Thread{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void run() {
        logger.info("MyThread start ...");
    }
}

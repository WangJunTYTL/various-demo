package com.peaceful.chain.demo;

import org.apache.commons.chain.Chain;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.Filter;
import org.apache.commons.chain.impl.ChainBase;
import org.apache.commons.chain.impl.ContextBase;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/6
 * @since 1.6
 */

public class FileDemoMain {

    // filter 也是一种command，它里面除了会有execute方法，也会新增一个postprocess 方法，放在chain里时postprocess一定会被执行
    // 暂时不知道有什么用处
    public static void main(String[] args) throws Exception {
        Filter filter = new FilterDemo();
        Chain chain = new ChainBase(filter);
        Context context = new ContextBase();
        chain.execute(context);
    }
}

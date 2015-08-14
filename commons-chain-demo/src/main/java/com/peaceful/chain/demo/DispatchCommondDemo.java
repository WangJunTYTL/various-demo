package com.peaceful.chain.demo;

import com.peaceful.common.util.Util;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.generic.DispatchCommand;
import org.apache.commons.chain.impl.ContextBase;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/6
 * @since 1.6
 */

public class DispatchCommondDemo extends DispatchCommand {



    public void say(Context context){
        Util.report("hello world");
    }


    public static void main(String[] args) throws Exception {
        DispatchCommand dispatchCommand = new DispatchCommondDemo();
        Context context = new ContextBase();
        dispatchCommand.setMethod("say");
        dispatchCommand.execute(context);
    }
}

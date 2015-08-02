package com.peaceful.chain.demo;

import com.peaceful.common.util.Util;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/2
 * @since 1.6
 */

public class Acommand implements Command {
    @Override
    public boolean execute(Context context) throws Exception {
        Util.report("A exe ...");
        context.put("A","true");
        return false;
    }
}

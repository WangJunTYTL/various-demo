package com.peaceful.chain.demo;

import com.peaceful.common.util.Util;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.Filter;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/6
 * @since 1.6
 */

public class FilterDemo implements Filter {


    @Override
    public boolean postprocess(Context context, Exception exception) {
        Util.report("postprocess...");
        return false;
    }

    @Override
    public boolean execute(Context context) throws Exception {
        Util.report("execute...");
        return false;
    }
}

package com.peaceful.chain.demo;

import com.peaceful.common.util.Util;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ChainBase;
import org.apache.commons.chain.impl.ContextBase;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/2
 * @since 1.6
 */

public class HandleABC {

    public static void main(String[] args) {
        Context context = new ContextBase();
        ChainBase command = new ChainBase();
        command.addCommand(new Acommand());
        command.addCommand(new Bcommand());
        command.addCommand(new Ccommand());
        try {
            command.execute(context);
            Util.report(context.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

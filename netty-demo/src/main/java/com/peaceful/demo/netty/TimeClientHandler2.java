package com.peaceful.demo.netty;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/24
 * @since 1.6
 */

import com.peaceful.common.util.Util;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

public class TimeClientHandler2 extends ChannelHandlerAdapter {


    //    经过TimeDecoder后msg对象已经变成UnixTime对象
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        UnixTime m = (UnixTime) msg;
        Util.report("服务器时间：" + m);
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        Util.report("channelActive：建立连接时调用");
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
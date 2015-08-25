package com.peaceful.demo.netty;

import com.peaceful.common.util.Util;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/24
 * @since 1.6
 */

public class EchoServerHandler extends ChannelHandlerAdapter {


    // 接收到消息调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //response 收到的request
        ctx.write(msg); // (1)
        ctx.flush(); // (2)
//        ctx.writeAndFlush(msg)
        //这时我们没有release msg，是因为当你响应request时，会自动释放掉了它
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        Util.report("channelActive：建立连接时调用");
    }

    // handler存在异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}

package com.peaceful.demo.netty;

import com.peaceful.common.util.Util;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/24
 * @since 1.6
 */

public class DiscardServerHandler2 extends ChannelHandlerAdapter {


    // 接收到消息调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //收到的消息是二进制消息
        ByteBuf byteBuf = (ByteBuf) msg;
        Util.report("丢弃所有收到的消息：" + byteBuf.release());
    }

    // handler存在异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}

package com.peaceful.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/25
 * @since 1.6
 */
/*
public class TimeEncoder extends ChannelHandlerAdapter {
   @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        UnixTime m = (UnixTime) msg;
        ByteBuf encoded = ctx.alloc().buffer(4);
        encoded.writeLong(m.value());
        ctx.write(encoded, promise); // (1)
    }
}*/


public class TimeEncoder extends MessageToByteEncoder<UnixTime> {
    @Override
    protected void encode(ChannelHandlerContext ctx, UnixTime msg, ByteBuf out) {
        out.writeLong(msg.value());
    }
}

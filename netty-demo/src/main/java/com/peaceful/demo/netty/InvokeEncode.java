package com.peaceful.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/25
 * @since 1.6
 */

public class InvokeEncode extends MessageToByteEncoder<InvokePOJO> {
    @Override
    protected void encode(ChannelHandlerContext ctx, InvokePOJO msg, ByteBuf out) throws Exception {
    }
}

package com.peaceful.demo.netty;

import com.peaceful.common.util.Util;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/25
 * @since 1.6
 */

public class TimeDecoder extends ByteToMessageDecoder { // (1)
    /*@Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) { // (2)
        if (in.readableBytes() < 4) {
            return; // (3)
        }

        out.add(in.readBytes(4)); // (4)
    }
*/
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if (in.readableBytes() < 4) {
            return;
        }
        // 把字节码数据转为pojo供下一个handler使用，是不是设计的很优雅很简单
        out.add(new UnixTime(in.readLong()));
    }
}

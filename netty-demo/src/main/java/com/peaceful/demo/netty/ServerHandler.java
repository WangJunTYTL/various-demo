package com.peaceful.demo.netty;

/**
 * Created by wangjun on 15/3/15.
 */

import com.peaceful.common.util.Util;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Handles a server-side channel.
 */
public class ServerHandler extends ChannelHandlerAdapter { // (1)
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws IOException { // (2)
        // Discard the received data silently.
        logger.info("hello world , I received msg ");
        ByteBuf in = (ByteBuf) msg;
        boolean flag = false;
        try {
/*            while (in.isReadable()) { // (1) 读取消息的效率低，麻烦
                bytes[i] = in.readByte();
                System.out.print((char) bytes[i]);
                System.out.flush();
//                Runtime.getRuntime().exec(String.valueOf((char) in.readByte()));
            }*/
            String res = in.toString(io.netty.util.CharsetUtil.US_ASCII);
            Util.report(res);
            if (res.equals("quit\r\n"))
                ctx.close();
            else {
                ctx.write(msg);
                ctx.flush(); // 这个时候已经释放掉接收的消息
                flag = true;
            }
        } finally {
            if (!flag) // 如果ctx把消息又写回了，此处不应执行
                ReferenceCountUtil.release(msg); // (2)
            logger.info("handle msg end");
        }
    }

    //channelActive()方法将会在连接被建立并且准备进行通信时被调用。因此让我们在这个方法里完成一个代表当前时间的32位整数消息的构建工作。
    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        final ByteBuf time = ctx.alloc().buffer(4); // (2)
//        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
        time.writeBytes("start...\n".getBytes());
        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                assert f == future;
            }
        }); // (4)

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}

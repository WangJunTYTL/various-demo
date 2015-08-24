package com.peaceful.demo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by wangjun on 15/3/21.
 */
public class ClientSetup {
    public static void main(String[] args) throws Exception {
        if (args.length == 0){
            args = new String[]{"127.0.0.1","8081"};
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ClientHandler());
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync(); // (5)

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            //关闭一个Netty应用往往只需要简单地通过shutdownGracefully()方法来关闭你构建的所有的NioEventLoopGroupS.
            // 当EventLoopGroup被完全地终止,并且对应的所有channels都已经被关闭时，Netty会返回一个Future对象。
            workerGroup.shutdownGracefully();
        }
    }
}

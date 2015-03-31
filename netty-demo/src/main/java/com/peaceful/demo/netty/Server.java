package com.peaceful.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by wangjun on 15/3/15.
 */
public class Server {

    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        //是用来处理I/O操作的多线程事件循环器
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //ServerBootstrap 是一个启动NIO服务的辅助启动类。你可以在这个服务中直接使用Channel，但是这会是一个复杂的处理过程，在很多情况下你并不需要这样做。
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) // (3) 这里我们指定使用NioServerSocketChannel类来举例说明一个新的Channel如何接收进来的连接。
                    .childHandler(new ChannelInitializer<SocketChannel>() { // (4)这里的事件处理类经常会被用来处理一个最近的已经接收的Channel。
                        // ChannelInitializer是一个特殊的处理类，他的目的是帮助使用者配置一个新的Channel。
                        // 也许你想通过增加一些处理类比如DiscardServerHandle来配置一个新的Channel或者其对应的ChannelPipeline来实现你的网络程序。
                        // 当你的程序变的复杂时，可能你会增加更多的处理类到pipline上，然后提取这些匿名类到最顶层的类上
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync(); // (7)

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    //可以通过telnet命令与它交互
    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new Server(port).run();
    }
}

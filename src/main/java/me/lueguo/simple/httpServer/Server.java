package me.lueguo.simple.httpServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import me.lueguo.simple.httpServer.initializer.MyHttpServerInitializer;

public class Server {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyHttpServerInitializer());
            ChannelFuture cf = serverBootstrap.bind(1124).sync();
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            System.out.println("fuck fuck");
        } finally {
        }
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
    }
}

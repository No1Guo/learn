package me.lueguo.codecDemo.client.application;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import me.lueguo.codecDemo.client.initializer.codecClientInitializer;

public class Client {

    private static final String HOST = "localhost";
    private static final int PORT = 4211;

    public static void main(String[] args) {

        EventLoopGroup eventExecutors = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    .handler(new codecClientInitializer());

            ChannelFuture cf = bootstrap.connect(HOST, PORT).sync();
            cf.channel().closeFuture().sync();

        }catch (Exception ex){

        }finally {
            eventExecutors.shutdownGracefully();
        }
    }
}

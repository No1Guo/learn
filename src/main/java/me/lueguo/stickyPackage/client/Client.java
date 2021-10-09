package me.lueguo.stickyPackage.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import me.lueguo.stickyPackage.client.initializer.ClientInitializer;

public class Client {

    private static final  String HOST  = "localhost";
    private static final  int PORT  =  4211;
    public static void main(String[] args) {
        EventLoopGroup eventExecutors = new NioEventLoopGroup();


        try {
            Bootstrap client = new Bootstrap();
            client.group(eventExecutors)
                    .channel(NioSocketChannel.class)
                            .handler(new ClientInitializer());

            ChannelFuture cf = client.connect(HOST, PORT).sync();
            cf.channel().closeFuture().sync();
        }catch (Exception ex){

        }finally {
            eventExecutors.shutdownGracefully();
        }
    }
}

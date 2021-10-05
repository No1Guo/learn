package me.lueguo.simple.firstNetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import me.lueguo.simple.firstNetty.Handler.SimpCliHandler;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 1124;

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup loopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap
                    .group(loopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println(ch.hashCode());
                            ch.pipeline().addLast(new SimpCliHandler());
                        }
                    });

            ChannelFuture cf = bootstrap.connect(HOST, PORT).sync();
            System.out.println("Client is ready for work...");
            cf.channel().closeFuture().sync();
        } finally {
            loopGroup.shutdownGracefully();
        }
    }
}

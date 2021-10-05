package me.lueguo.simple.firstNetty.Initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import me.lueguo.simple.firstNetty.Handler.SimpServHandler;

public class TestChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        System.out.println(ch.hashCode());
        ch.pipeline().addLast(new SimpServHandler());
    }
}

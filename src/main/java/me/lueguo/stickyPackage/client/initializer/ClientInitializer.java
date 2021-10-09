package me.lueguo.stickyPackage.client.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import me.lueguo.stickyPackage.client.handler.ClientHandler;
import me.lueguo.stickyPackage.codec.UserProtoDecoder;
import me.lueguo.stickyPackage.codec.UserProtoEncoder;

public class ClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new UserProtoDecoder())
                .addLast(new UserProtoEncoder()) //OutboundHandler
                .addLast(new ClientHandler());
    }
}

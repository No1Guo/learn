package me.lueguo.stickyPackage.server.initializer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import me.lueguo.stickyPackage.codec.UserProtoDecoder;
import me.lueguo.stickyPackage.codec.UserProtoEncoder;
import me.lueguo.stickyPackage.server.handler.ServerHandler;

public class ServerInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new UserProtoEncoder())
                .addLast(new UserProtoDecoder())
                .addLast(new ServerHandler());
    }
}

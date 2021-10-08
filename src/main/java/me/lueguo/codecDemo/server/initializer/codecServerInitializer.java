package me.lueguo.codecDemo.server.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import me.lueguo.codecDemo.server.handler.codec.deByteToLong;
import me.lueguo.codecDemo.server.handler.codec.enLongToByte;
import me.lueguo.codecDemo.server.handler.logic.serverHandler;

public class codecServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new deByteToLong());
        pipeline.addLast(new enLongToByte());

        pipeline.addLast(new serverHandler());

    }
}

package me.lueguo.codecDemo.client.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import me.lueguo.codecDemo.client.handler.codec.deByteToLong;
import me.lueguo.codecDemo.client.handler.codec.enLongToByte;
import me.lueguo.codecDemo.client.handler.logic.clientHandler;

public class codecClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new deByteToLong());
        pipeline.addLast(new enLongToByte());

        pipeline.addLast(new clientHandler());
    }
}

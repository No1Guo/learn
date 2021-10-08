package me.lueguo.codecDemo.client.handler.logic;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class clientHandler extends SimpleChannelInboundHandler<Long> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        Channel channel = ctx.channel();

        System.out.println("From Server [" + channel.remoteAddress() + "], is a Long Data: " + msg);

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        System.out.println("------------------channelActive called send data------------------" );
        Long msg = 991124L;
        System.out.println("------------------[991124|Long]-----------------");
        channel.writeAndFlush(msg);
    }
}

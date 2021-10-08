package me.lueguo.codecDemo.server.handler.logic;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class serverHandler extends SimpleChannelInboundHandler<Long> {
    private final int SEC = 1000;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("From Client [" + channel.remoteAddress() + "], is a Long Data: " + msg);

        System.out.println("Now the msg plus 1 and send back ");
        msg++;
        Thread.sleep(3 * SEC);
        channel.writeAndFlush(msg);
    }
}

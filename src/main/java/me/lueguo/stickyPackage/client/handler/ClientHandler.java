package me.lueguo.stickyPackage.client.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import me.lueguo.stickyPackage.entity.UserProto;

public class ClientHandler extends SimpleChannelInboundHandler<UserProto> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, UserProto msg) throws Exception {
        int length = msg.getLength();
        String content = msg.getContent();
        System.out.println("------------ Unpacking Message From Server ------------"  );
        System.out.println("length:" + length + "|content:" + content);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        for (int i = 0; i < 4; i++) {
            String content = "He fuck dog, already fucked times: " + i;
            int length = content.length();
            UserProto msg = new UserProto(length, content);
            channel.writeAndFlush(msg);
        }
    }
}

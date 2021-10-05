package me.lueguo.nettyChat.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;


public class ChatServerHandler extends ChannelInboundHandlerAdapter {

    private static ChannelGroup channelGroup =  new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel currentCh = ctx.channel();

        String actMsg = "Client [" + currentCh.remoteAddress() + "] is online.";
        ByteBuf byteBuf = Unpooled.copiedBuffer(actMsg, CharsetUtil.UTF_8);
        channelGroup.add(currentCh);
        channelGroup.writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel currentCh = ctx.channel();
        ByteBuf byteBuf = (ByteBuf) msg;
        String ReadMsg ="[" + currentCh.remoteAddress() + "]: " + byteBuf.toString(CharsetUtil.UTF_8);


        System.out.println(ReadMsg);

//        channelGroup.writeAndFlush(byteBuf);
    }
}

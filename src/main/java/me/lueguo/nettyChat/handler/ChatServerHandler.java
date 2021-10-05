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

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel currentCh = ctx.channel();
        channelGroup.add(currentCh);
        String actMsg = "Client [" + currentCh.remoteAddress() + "] is online.";
        ByteBuf byteBuf = Unpooled.copiedBuffer(actMsg, CharsetUtil.UTF_8);

        channelGroup.writeAndFlush(byteBuf);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel currentCh = ctx.channel();
        channelGroup.remove(currentCh);
        String offlineMsg = "[" + currentCh.remoteAddress() + "] is offline.";
        ByteBuf byteBuf = Unpooled.copiedBuffer(offlineMsg, CharsetUtil.UTF_8);

        channelGroup.writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel currentCh = ctx.channel();
        ByteBuf byteBuf = (ByteBuf) msg;

        String content = byteBuf.toString(CharsetUtil.UTF_8);

        sendChatMsg(currentCh,content);

    }

    private void sendChatMsg(Channel ch,String msg){
        for (Channel c: channelGroup
        ) {
            if( ch != c){
                c.writeAndFlush(Unpooled.copiedBuffer("[" + ch.remoteAddress() + "]: "  + msg,CharsetUtil.UTF_8));
            }else {
                c.writeAndFlush( Unpooled.copiedBuffer("[You]: " + msg,CharsetUtil.UTF_8));
            }
        }
    }
}

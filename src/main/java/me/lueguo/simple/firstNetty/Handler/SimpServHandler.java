package me.lueguo.simple.firstNetty.Handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class SimpServHandler extends ChannelInboundHandlerAdapter {
    private static final int SEC = 1000;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("------------------------------------Server Read Message------------------------------------");
        Channel channel = ctx.channel();
        System.out.println("ops pushing in taskQueue...");
        channel.eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * SEC);
                    String compMsg = "Hello , Big Fucker!";
                    ctx.writeAndFlush(Unpooled.copiedBuffer(compMsg,CharsetUtil.UTF_8));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        ByteBuf byteBuf = (ByteBuf) msg;
        String readMsg = byteBuf.toString(CharsetUtil.UTF_8);
        System.out.println(readMsg);
        System.out.println( "From Client : [" + channel.remoteAddress() + "]");
        System.out.println("------------------------------------Server Read Message------------------------------------");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete...");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Server exceptionCaught, Channel is closing...");
        ctx.close();
        System.out.println("Channel closed.");
    }
}

package me.lueguo.stickyPackage.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;
import me.lueguo.stickyPackage.entity.UserProto;

public class ServerHandler extends SimpleChannelInboundHandler<UserProto> {
    private static final EventExecutorGroup group = new DefaultEventLoopGroup(2);
    private static final int SEC = 1000;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, UserProto msg) throws Exception {
        Channel channel = ctx.channel();

        int length = msg.getLength();
        String content = msg.getContent();

        System.out.println("----------- Read message -----------");
        System.out.println("length:" + length + "|content:" + content);

        group.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5 * SEC);
                    channel.writeAndFlush(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}

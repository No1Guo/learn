package me.lueguo.nettyChat;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import me.lueguo.nettyChat.initializer.ChatClientInitializer;

import java.util.Scanner;

public class ChatClient {

    private static final String HOST = "localhost";
    private static final int PORT = 1124;

    private void sendMsg(Channel channel){
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()){
            String msg = scan.nextLine();
            ByteBuf byteBuf = Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8);
            channel.writeAndFlush(byteBuf);
        }
    }

    public static void main(String[] args) {

        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    .handler(new ChatClientInitializer());

            ChannelFuture cf = bootstrap.connect(HOST, PORT).sync();
            Channel channel = cf.channel();
            new ChatClient().sendMsg(channel);

            cf.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventExecutors.shutdownGracefully();
        }
    }
}

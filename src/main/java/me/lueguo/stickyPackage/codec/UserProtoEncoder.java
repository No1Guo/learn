package me.lueguo.stickyPackage.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;
import me.lueguo.stickyPackage.entity.UserProto;

public class UserProtoEncoder extends MessageToByteEncoder<UserProto> {
    @Override
    protected void encode(ChannelHandlerContext ctx, UserProto msg, ByteBuf out) throws Exception {
        System.out.println("--------------- encode ---------------");
        System.out.println("From pre handler, under UserProto, now encoding...");
        int length = msg.getLength();
        byte[] content = msg.getContent().getBytes(CharsetUtil.UTF_8);
        System.out.println("UserProto encoded, now writing...");
        out.writeInt(length);
        out.writeBytes(content);

    }
}

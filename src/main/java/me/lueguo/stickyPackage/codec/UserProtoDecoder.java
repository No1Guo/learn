package me.lueguo.stickyPackage.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;
import me.lueguo.stickyPackage.entity.UserProto;

import java.util.List;

public class UserProtoDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("--------------- decode ---------------");
        System.out.println("Received bytes, now decoding...");
        int length = in.readInt();
        ByteBuf byteBuf = in.readBytes(length);
        String content = byteBuf.toString(CharsetUtil.UTF_8);
        System.out.println("Decoded. Packing to UserProto.");
        UserProto msg = new UserProto(length, content);
        out.add(msg);
        System.out.println("msg sending to the next Handler...");

    }
}

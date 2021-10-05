package me.lueguo.simple.unpoolTest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class TestMain {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.buffer(10);

        int capacity = byteBuf.capacity();

        for (int i = 0; i < 10; i++) {
            byteBuf.writeByte(i);
        }

        for (int i = 0; i < capacity; i++) {
            byteBuf.readByte();
        }

        byteBuf.resetReaderIndex();
        for (int i = 0; i < capacity; i++) {
            byteBuf.readByte();
        }
    }
}

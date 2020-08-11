package com.wzhhan.distriquartz.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author:monsterHan
 * @date:2020/5/8-21:27
 * @description:@TODO
 */
public class SocketClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        UnpooledByteBufAllocator allocator = new UnpooledByteBufAllocator(false);
        ByteBuf buffer = allocator.buffer(20);
        buffer.writeInt(1604);
        buffer.writeBytes("head".getBytes());
        String longMsgBody = "";
        for (int i = 0; i < 400; i++) {
            longMsgBody = longMsgBody + "body";
        }
        buffer.writeBytes(longMsgBody.getBytes());

        ctx.writeAndFlush(buffer);

//        for (int i = 0; i < 20; i++) {
//            UnpooledByteBufAllocator allocator = new UnpooledByteBufAllocator(false);
//            ByteBuf buffer = allocator.buffer(20);
//            buffer.writeInt(8);
//            buffer.writeBytes("head".getBytes());
//            buffer.writeBytes("body".getBytes());
//
//            ctx.writeAndFlush(buffer);
//        }
//        Thread.sleep(20000);
//        for (int i = 0; i < 10; i++) {
//            UnpooledByteBufAllocator allocator = new UnpooledByteBufAllocator(false);
//            ByteBuf buffer = allocator.buffer(20);
//            buffer.writeInt(8);
//            buffer.writeBytes("head".getBytes());
//            buffer.writeBytes("body".getBytes());
//
//            ctx.writeAndFlush(buffer);
//        }

    }
}

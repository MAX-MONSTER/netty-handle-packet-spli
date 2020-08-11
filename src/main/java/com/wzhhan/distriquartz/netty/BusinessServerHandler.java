package com.wzhhan.distriquartz.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
/**
 * @author:monsterHan
 * @date:2020/5/8-21:22
 * @description:@TODO
 */
public class BusinessServerHandler extends ChannelInboundHandlerAdapter {
    private static int count = 0;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("BusinessServerHandler call count="+ ++count);
        ByteBuf buf = (ByteBuf)msg;
        int length = buf.readInt();
        assert length == (8);

        byte[] head = new byte[4];
        buf.readBytes(head);
        String headString = new String(head);
        assert  "head".equals(headString);

        System.out.println("head:"+headString);
        byte[] body = new byte[4];
        buf.readBytes(body);
        String bodyString = new String(body);
        assert  "body".equals(bodyString);
        System.out.println("body:"+bodyString);
        buf.release();
    }
}


package com.wzhhan.distriquartz.netty;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
/**
 * @author:monsterHan
 * @date:2020/5/8-21:23
 * @description:@TODO
 */
public class SocketClientInitializer  extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new SocketClientHandler());
    }
}



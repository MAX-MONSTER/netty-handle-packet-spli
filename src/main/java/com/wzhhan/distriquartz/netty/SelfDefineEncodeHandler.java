package com.wzhhan.distriquartz.netty;

import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

/**
 * @author:monsterHan
 * @date:2020/5/8-21:05
 * @description:@TODO
 */
public class SelfDefineEncodeHandler extends ByteToMessageDecoder {
    private static int count = 0;
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf bufferIn, List<Object> out) throws Exception {
        if (bufferIn.readableBytes() < 4) {
            return;
        }

        int beginIndex = bufferIn.readerIndex();

        System.out.println("decode call count="+ ++count);
        System.out.println("bufferIn.readableBytes()="+bufferIn.readableBytes());
        System.out.println("beginIndex= "+beginIndex);
        int length = bufferIn.readInt();
        //如果没有足够的数据就return  处理半包问题  由于第一次调用中readerIndex被重置为0,那么decode方法被调用第二次的时候,beginIndex还是为0的
        if (bufferIn.readableBytes() < length) {
            bufferIn.readerIndex(beginIndex);
            return;
        }
       // 对于粘包这种场景,这行代码就不是表示将readerIndex升到最高,而是将readerIndex后移(length+4)位，让beginIndex递增(length+4)。
        bufferIn.readerIndex(beginIndex + 4 + length);


        //slice操作,目的是从大消息中截取出一条有效的业务消息（就是切出来一个包）
        ByteBuf otherByteBufRef = bufferIn.slice(beginIndex, 4 + length);
        //增加引用 不然 到了handler就会被GC清空
        otherByteBufRef.retain();
        out.add(otherByteBufRef);


    }

}

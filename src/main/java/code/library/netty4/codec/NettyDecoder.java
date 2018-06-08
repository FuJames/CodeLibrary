package code.library.netty4.codec;

import code.library.serializer.Serializer;
import code.library.serializer.SerializerFactory;
import code.library.serializer.SerializerType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author fuqianzhong
 * @date 18/6/1
 * 字节流转化为对象
 */
public class NettyDecoder extends ByteToMessageDecoder {
    private Class<?> msgClass;

    public NettyDecoder(Class<?> msgClass){
        this.msgClass = msgClass;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }
        byte serialize = in.readByte();
        Serializer serializer = SerializerFactory.getSerializer(SerializerType.getSerializerType(serialize));
        in.markReaderIndex();
        int dataLength = in.readInt();
        if (dataLength < 0) {
            ctx.close();
        }
        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }
        byte[] data = new byte[dataLength];
        in.readBytes(data);
        Object obj = serializer.deserialize(data, msgClass);
        out.add(obj);
    }
}

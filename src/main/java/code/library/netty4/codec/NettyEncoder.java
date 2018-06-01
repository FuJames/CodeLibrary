package code.library.netty4.codec;

import code.library.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author fuqianzhong
 * @date 18/6/1
 * encode:对象转成字节流
 */
public class NettyEncoder extends MessageToByteEncoder<Object> {
    private Class<?> msgClass;
    private Serializer serializer;

    public NettyEncoder(Class<?> msgClass,Serializer serializer){
        this.msgClass = msgClass;
        this.serializer = serializer;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if(msgClass.isInstance(msg)){
            byte[] bytes = serializer.serialize(msg);
            out.writeInt(bytes.length);
            out.writeBytes(bytes);
        }
    }
}

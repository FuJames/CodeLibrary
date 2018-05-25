package code.library.netty4.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * Created by fuqianzhong on 16/9/30.
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    /**
     * 当client与server连接成功后调用
     * @param context
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext context)throws Exception{
        context.writeAndFlush(Unpooled.copiedBuffer("Hello server", CharsetUtil.UTF_8));
    }

    /**
     * client从channel中读取服务器返回的内容
     * @param context
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext context, ByteBuf msg) throws Exception {
        System.out.println("Client received : " + msg.toString(CharsetUtil.UTF_8));
    }

    /**
     * 当发送异常时,关闭资源
     * @param context
     * @param throwable
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable throwable)throws Exception{
        throwable.printStackTrace();
        context.close();
    }
}

package code.library.netty4.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Date;

/**
 * Created by fuqianzhong on 16/9/30.
 * 自定义SocketChannel的数据读写类,重写ChannelHandlerAdapter的channelRead方法,就可以完成channel的读和写
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 服务器从channel中读取服务,当有数据到达channel时,被触发
     * 在channeRead编写与client端数据交互的逻辑
     * @param context, 与SocketChannel交互的对象
     * @param msg, client端发送过来的消息
     */
    @Override
    public void channelRead(ChannelHandlerContext context, Object msg)throws Exception{
        ByteBuf byteBuf = (ByteBuf) msg;
        String clientData = byteBuf.toString(CharsetUtil.UTF_8);
        System.out.println("Server received : "+clientData);
        ByteBuf response = Unpooled.copiedBuffer("Server time : " + new Date().toString(), CharsetUtil.UTF_8);
        context.write(response);//读和写都是针对ByteBuf对象
    }

    /**
     * netty服务端从client读的消息是一段一段过来的,这个方法会在最后一条消息读完后执行;
     * netty不会直接把消息发送给客户端,而是先通过write方法,将消息写入缓冲区,再通过调用flush方法,唤醒Selector把数据写入Channel,
     * 你也可以通过writeAndFlush方法实时的把数据写回客户端
     * @param context
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext context)throws Exception{
        context.flush();
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

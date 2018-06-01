package code.library.netty4.handler;

import code.library.netty4.codec.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by fuqianzhong on 16/9/30.
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<RpcResponse> {
    /**
     * 当client与server连接成功后调用
     * @param context
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext context)throws Exception{
    }

    /**
     * client从channel中读取服务器返回的内容
     * @param context
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext context, RpcResponse msg) throws Exception {
        if(msg != null){
            System.out.println(msg);
        }
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

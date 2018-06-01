package code.library.netty4.handler;

import code.library.common.dto.UserDTO;
import code.library.netty4.codec.RpcRequest;
import code.library.netty4.codec.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by fuqianzhong on 16/9/30.
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<RpcRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequest msg) throws Exception {
        System.out.println("Server received : " + msg);
        RpcResponse response = new RpcResponse();
        UserDTO userDTO = new UserDTO();
        userDTO.setAge(20);
        userDTO.setName("fqz");
        userDTO.setAddress("Mars");
        response.setResult(userDTO);
        response.setRequestId(msg.getRequestId());
        ctx.write(response);
    }

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

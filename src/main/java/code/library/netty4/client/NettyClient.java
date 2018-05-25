package code.library.netty4.client;

import code.library.netty4.handler.NettyClientHandler;
import code.library.zk.registry.ServiceRegistry;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by fuqianzhong on 16/9/30.
 */
public class NettyClient {
    public void connect(String ip,int port){
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();//客户端使用Bootstrap类
            bootstrap.group(workerGroup).channel(NioSocketChannel.class)
                     .option(ChannelOption.TCP_NODELAY,true)
                     .handler(new ChannelInitializer<SocketChannel>() {
                         @Override
                         protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyClientHandler());
                         }
                     });
            ChannelFuture channelFuture = bootstrap.connect(ip,port).sync();//发起异步连接操作,没有连接成功之前会阻塞本身,但不会占用cpu
            channelFuture.channel().closeFuture().sync();
        }catch (Exception ex){
            ex.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        ServiceRegistry registry = new ServiceRegistry();
        new NettyClient().connect("127.0.0.1",1300);
    }
}

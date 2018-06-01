package code.library.netty4.server;

import code.library.netty4.handler.NettyServerHandler;
import code.library.serializer.HessianSerializer;
import code.library.serializer.Serializer;
import code.library.utils.NetUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by fuqianzhong on 16/9/30.
 * Netty Nio Server
 * 1. 一切从ServerBootStrap开始
 * 2. 设置Reactor线程池,bossGroup用来处理客户端的连接(acceptor),workerGroup用来处理ChannelSocket的读写(worker thread)
 */
public class NettyServer {
    public void doStart(final int port, final Serializer serializer){
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            bootstrap.group(bossGroup,workerGroup)
                     .channel(NioServerSocketChannel.class)
                     .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,1000)
                     .childHandler(new ChannelInitializer<SocketChannel>() {
                         @Override
                         protected void initChannel(SocketChannel socketChannel) throws Exception {
                             socketChannel.pipeline()
//                                     .addLast(new NettyEncoder())
                                     .addLast(new NettyServerHandler());
                         }
                     });//设置channel的处理类,使用责任链模式,channel对应管道(pipeline),向管道中添加处理类
            ChannelFuture cf = bootstrap.bind(port).sync();//Server绑定到端口,等待连接,同步阻塞的方式
            System.out.println(NettyServer.class.getName() + " started and listen on " + cf.channel().localAddress());
            cf.channel().closeFuture().sync();//同步阻塞的方式等待服务器端口关闭
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();//释放线程池资源
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = NetUtil.getAvailablePort(0);
        new NettyServer().doStart(1300, new HessianSerializer());
    }
}

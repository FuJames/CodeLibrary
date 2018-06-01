package code.library.netty4.client;

import code.library.netty4.handler.NettyClientHandler;
import code.library.netty4.server.codec.RpcRequest;
import code.library.zk.registry.ServerInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by fuqianzhong on 16/9/30.
 */
public class NettyClient {
    private ServerInfo server;

    private Channel channel;

    public NettyClient(ServerInfo server){
        this.server = server;
        initClient();
    }

    private void initClient(){
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
            ChannelFuture channelFuture = bootstrap.connect(server.getIp(),server.getPort()).sync();//发起异步连接操作,没有连接成功之前会阻塞本身,但不会占用cpu
            channel = channelFuture.channel();
            channel.closeFuture().sync();
        }catch (Exception ex){
            ex.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    public boolean isValidate() {
        if (this.channel != null) {
            return this.channel.isActive();
        }
        return false;
    }

    public void close() {
        if (this.channel != null) {
            if (this.channel.isOpen()) {
                this.channel.close();
            }
        }
    }

    public void send(RpcRequest request) throws Exception {
        this.channel.writeAndFlush(request).sync();
    }

    public static void main(String[] args) {
        ServerInfo serverInfo = new ServerInfo();
        serverInfo.setIp("localhost");
        serverInfo.setPort(1300);
        NettyClient client = new NettyClient(serverInfo);
//        RpcRequest
//        client.send();
    }

}

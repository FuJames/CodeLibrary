package code.library.netty4.client;

import code.library.netty4.codec.NettyDecoder;
import code.library.netty4.codec.NettyEncoder;
import code.library.netty4.codec.RpcResponse;
import code.library.netty4.handler.NettyClientHandler;
import code.library.netty4.codec.RpcRequest;
import code.library.serializer.HessianSerializer;
import code.library.serializer.Serializer;
import code.library.zk.registry.ServerInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.UUID;

/**
 * Created by fuqianzhong on 16/9/30.
 */
public class NettyClient {
    private ServerInfo server;
    private Serializer serializer;
    private Channel channel;
    private EventLoopGroup workerGroup;


    public NettyClient(ServerInfo server, Serializer serializer){
        this.server = server;
        this.serializer = serializer;
        initClient();
    }

    private void initClient(){
        try{
            workerGroup = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap();//客户端使用Bootstrap类
            bootstrap.group(workerGroup).channel(NioSocketChannel.class)
                     .option(ChannelOption.TCP_NODELAY,true)
                     .handler(new ChannelInitializer<SocketChannel>() {
                         @Override
                         protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast(new NettyEncoder(RpcRequest.class,serializer))
                                    .addLast(new NettyDecoder(RpcResponse.class,serializer))
                                    .addLast(new NettyClientHandler());
                         }
                     });
            ChannelFuture channelFuture = bootstrap.connect(server.getIp(),server.getPort()).sync();//发起异步连接操作,没有连接成功之前会阻塞本身,但不会占用cpu
            channel = channelFuture.channel();
        }catch (Exception ex){
            ex.printStackTrace();
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
        if(workerGroup != null){
            workerGroup.shutdownGracefully();
        }
    }

    public void send(RpcRequest request) throws Exception {
        this.channel.writeAndFlush(request).sync();
    }

    public static void main(String[] args) throws Exception {
        ServerInfo serverInfo = new ServerInfo();
        serverInfo.setIp("localhost");
        serverInfo.setPort(1300);
        NettyClient client = new NettyClient(serverInfo, new HessianSerializer());
        RpcRequest request = new RpcRequest();
        request.setRequestId(UUID.randomUUID().toString());
        request.setClassName("fqz");
        request.setMethodName("god");
        request.setCreateMillisTime(System.currentTimeMillis());
        client.send(request);
    }

}

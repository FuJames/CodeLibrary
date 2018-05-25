package code.library.zk.registry;

import code.library.netty4.client.NettyClient;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author fuqianzhong
 * @date 18/5/21
 * 客户端管理类,用于保存客户-服务端连接
 */
public class ClientManager {
    private static final ConcurrentHashMap<String, Set<ServerInfo>> servers = new ConcurrentHashMap<String, Set<ServerInfo>>();

    public void registerClient(String serviceKey) throws Exception {
        //从注册中心获取服务端地址
        String serviceAddr = ServiceRegistry.getServiceAddr(serviceKey);

        //保存服务端地址到本地
        Set<ServerInfo> serverSet = ServerUtils.parseServerAddress(serviceAddr);
        servers.put(serviceKey, serverSet);

        //遍历服务端地址,建立netty连接
        for(ServerInfo serverInfo : serverSet){
            new NettyClient().connect(serverInfo.getIp(),serverInfo.getPort());
        }

    }

    public void addServer(){

    }

    public void removeServer(){

    }
}

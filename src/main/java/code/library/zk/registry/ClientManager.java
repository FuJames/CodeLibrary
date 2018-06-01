package code.library.zk.registry;

import code.library.netty4.client.NettyClient;
import code.library.serializer.HessianSerializer;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author fuqianzhong
 * @date 18/5/21
 * 客户端管理类,用于保存客户-服务端连接
 */
public class ClientManager {
    private static final ConcurrentHashMap<String, Set<ServerInfo>> servers = new ConcurrentHashMap<String, Set<ServerInfo>>();

    public static void registerClient(String serviceKey) throws Exception {
        //从注册中心获取服务端地址
        String serviceAddr = ServiceRegistry.getServiceAddr(serviceKey);

        //保存服务端地址到本地
        Set<ServerInfo> serverSet = ServerUtils.parseServerAddress(serviceAddr);

        addServers(serviceKey,serverSet);

    }

    public static void addServers(String serviceKey,Set<ServerInfo> serverSet) {
        //遍历服务端地址,建立netty连接
        for(ServerInfo serverInfo : serverSet){
            NettyClient client = new NettyClient(serverInfo, new HessianSerializer());
//            client.doConnect();
        }
        Set<ServerInfo> serverSetOld = servers.get(serviceKey);
        if(serverSetOld == null){
            serverSetOld = Collections.newSetFromMap(new ConcurrentHashMap<ServerInfo, Boolean>());
        }
        serverSetOld.addAll(serverSet);
        servers.put(serviceKey, serverSetOld);
    }

    public static void removeServers(String serviceKey, Set<ServerInfo> serverSet) {
        Set<ServerInfo> serverSetOld = servers.get(serviceKey);
        if(serverSetOld != null){
            serverSetOld.removeAll(serverSet);
        }
    }

    public static Set<ServerInfo> getServers(String serviceKey){
        return servers.get(serviceKey);
    }

}

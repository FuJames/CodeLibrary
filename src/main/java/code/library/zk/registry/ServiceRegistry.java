package code.library.zk.registry;

import code.library.zk.curator.CuratorClient;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author fuqianzhong
 * @date 18/5/21
 * 服务注册中心,
 * 1. 服务端启动时,连接到注册中心,向zk写入节点,节点的路径=/SERVER/+{serviceKey},节点数据为服务的ip:port字符串,多个以逗号隔开
 * 2. 客户端启动时,连接到注册中心,根据serviceKey获取节点的数据,即ip:port列表,拿到服务端列表后,创建连接
 * 3. 客户端监听节点事件,当服务改变时,通知客户端
 */
public class ServiceRegistry {
    private static final String SERVICE_PATH = "/SERVER/";

    private static final String ZK_HOSTS = "localhost:3181,localhost:3182,localhost:3183";

    private static CuratorClient curatorClient;

    public ServiceRegistry() throws Exception {
        curatorClient = new CuratorClient(ZK_HOSTS);
    }

    public void registerService(ServerConfig serverConfig) throws Exception {
        String path = SERVICE_PATH + serverConfig.getServiceKey();
        //注册临时节点,当会话消失后,节点也会被删除
        curatorClient.createEphemeral(path);
        String serviceAddr = curatorClient.getData(path);
        if(!serviceAddr.isEmpty()){
            serviceAddr += ",";
        }
        serviceAddr += serverConfig.getIp()+":"+serverConfig.getPort();
        curatorClient.setData(path,serviceAddr);
    }

    public static String getServiceAddr(String serviceKey) throws Exception {
        String path = SERVICE_PATH + serviceKey;
        return curatorClient.getData(path);
    }

}

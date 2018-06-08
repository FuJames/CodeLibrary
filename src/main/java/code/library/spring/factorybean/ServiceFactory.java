package code.library.spring.factorybean;

import code.library.netty4.server.NettyServer;
import code.library.spring.RpcService;
import code.library.zk.registry.RegistryManager;
import code.library.zk.registry.ServiceInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author fuqianzhong
 * @date 18/6/5
 * 1. 获取所有基于注解的Rpc服务类,将服务bean缓存到本地
 * 2. 将服务注册到注册中心
 * 3. 启动netty服务
 */
public class ServiceFactory implements ApplicationContextAware,InitializingBean{
    private int port = 1300;
    private String ip;

    private static Map<String,Object> services = new ConcurrentHashMap<String,Object>();

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        //bean key & bean
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);
        System.out.println(serviceBeanMap);
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }
        if(serviceBeanMap != null && serviceBeanMap.size() > 0){
            for(Map.Entry<String,Object> entry : serviceBeanMap.entrySet()){
                String serviceKey = entry.getValue().getClass().getAnnotation(RpcService.class).serviceKey();
                if(StringUtils.isNotBlank(serviceKey)){
                    services.put(serviceKey, entry.getValue());
                    try {
                        ServiceInfo serviceInfo = new ServiceInfo();
                        serviceInfo.setServiceKey(serviceKey);
                        serviceInfo.setIp(ip);
                        serviceInfo.setPort(port);
                        RegistryManager.getInstance().registerService(serviceInfo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            new NettyServer().doStart(port);
        }
    }

    public static Object getServiceBean(String serviceKey){
        return services.get(serviceKey);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}

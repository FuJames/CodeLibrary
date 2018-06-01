package code.library.zk.registry;

import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;
/**
 * @author fuqianzhong
 * @date 18/5/21
 */
public class ServerUtils {
    public static Set<ServerInfo> parseServerAddress(String serverAddr){
        if(StringUtils.isEmpty(serverAddr)){
            return null;
        }
        Set<ServerInfo> serverSet = new HashSet<ServerInfo>();

        String[] addrs = serverAddr.split(",");
        for(String addr : addrs){
            String[] parts = addr.split(":");
            String host = parts[0];
            String port = parts[1];
            ServerInfo serverInfo = new ServerInfo();
            serverInfo.setIp(host);
            serverInfo.setPort(Integer.parseInt(port));
            serverSet.add(serverInfo);
        }
       return serverSet;
    }

    public static String parseServiceKey(String path) {
        if(StringUtils.isEmpty(path)){
            return null;
        }
        int index = path.lastIndexOf("/");
        if(index > 0){
            return path.substring(index+1);
        }
        return null;
    }
}

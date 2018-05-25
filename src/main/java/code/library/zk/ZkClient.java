package code.library.zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author fuqianzhong
 * @date 18/5/18
 * zk的存储是以树型结构为基础,每个树节点由路径、数据、权限、节点类型组成
 */
public class ZkClient {
    private static final String HOSTS = "localhost:3181,localhost:3182,localhost:3183";
    private static final int SESSION_TIMEOUT = 5000;
    private ZooKeeper zk = null;

    private ZkClient(){}

    public void connect(){
        try {
            zk = new ZooKeeper(HOSTS, SESSION_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if(event.getState() == Event.KeeperState.SyncConnected){
                        System.out.println("Watcher,"+event.getType());
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        if (null != zk) {
            try {
                zk.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 创建节点
     * @param path
     * @param data
     * @param type
     */
    public boolean createNode(String path, String data, CreateMode type) throws KeeperException, InterruptedException {
        //节点不存在时,创建节点,否则会报错
        if(zk != null && zk.exists(path, true) == null){
            zk.create(path, data.getBytes(Charset.forName("utf8")), ZooDefs.Ids.OPEN_ACL_UNSAFE,type);
            return true;
        }
        return false;
    }

    /**
     * 获取节点的子节点列表
     * @param path
     * @return
     */
    public List<String> getChildNodes(String path) throws KeeperException, InterruptedException {
        List<String> list = zk.getChildren(path, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("getChildNodes watcher," + event.getType());
            }
        });
        return list;
    }

    public String getData(String path) throws KeeperException, InterruptedException {
        byte[] bytes = zk.getData(path, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("getData watcher,"+event.getType());
            }
        },null);
        return new String(bytes,Charset.forName("utf8"));
    }

    public void setData(String path, String data) throws KeeperException, InterruptedException {
        zk.setData(path,data.getBytes(Charset.forName("utf8")),-1);
    }

    /**
     * 节点不存在或者有子节点,删除失败
     * @param path
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void deleteNode(String path) throws KeeperException, InterruptedException {
        zk.delete(path,-1);
    }




    public static void main(String[] args) throws KeeperException, InterruptedException {
        ZkClient zk = new ZkClient();
        zk.connect();
        String path = "/test";
        zk.createNode(path,"hello world", CreateMode.PERSISTENT);
        zk.createNode(path + "/child","hello child", CreateMode.PERSISTENT);
        System.out.println(zk.getChildNodes("/"));

        zk.setData(path, "hello 2");
        System.out.println(zk.getData(path));
        zk.deleteNode(path + "/child");
        System.out.println(zk.getChildNodes("/test"));

        zk.closeConnection();

    }
}

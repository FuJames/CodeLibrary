package code.library.zk.curator;

import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * @author fuqianzhong
 * @date 18/5/19
 */
public class CuratorTester {
    private static final String HOSTS = "localhost:3181,localhost:3182,localhost:3183";

    public static void main(String[] args) throws Exception {
        Calendar cale = Calendar.getInstance();
        int day =  cale.get(Calendar.DATE);
        cale.set(Calendar.DATE, day - 1);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd000000");
        String firstday, lastday;
        cale.add(Calendar.MONTH, -1);
        firstday = format.format(cale.getTime());
        cale.add(Calendar.MONTH, 1);
        lastday = format.format(cale.getTime());


        CuratorClient client = new CuratorClient(HOSTS);

        client.createIsAbsent("/curator");

        List<String> list = client.getChildren("/");
        System.out.println(list);

        CuratorWatcher watcher = new CuratorWatcher() {
            @Override
            public void process(WatchedEvent event) throws Exception {
                switch (event.getType()){
                    case NodeDataChanged:
                        System.out.println("[ZK Curator], node data changed for, " + event.getPath());
                        break;
                    case NodeDeleted:
                        System.out.println("[ZK Curator], node delete for, " + event.getPath());
                        break;
                    default:
                }
            }
        };
        client.getData("/curator", watcher);

        client.setData("/curator","hello curator");

        client.close();
    }
}

package code.library.zk.registry;

/**
 * @author fuqianzhong
 * @date 18/6/5
 */
public interface ServiceChangeListener {
    void serverAdded(String serviceKey, ServerInfo serverInfo) throws Exception;
    void serverRemoved(String serviceKey, ServerInfo serverInfo) throws Exception;
}

package code.library.netty4.client;

import code.library.netty4.codec.RpcRequest;
import code.library.netty4.codec.RpcResponse;

/**
 * @author fuqianzhong
 * @date 18/6/5
 */
public interface Client {
    RpcResponse sendRequest(RpcRequest request);
}

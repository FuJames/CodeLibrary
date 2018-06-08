package code.library.netty4.codec;

import java.io.Serializable;

/**
 * @author fuqianzhong
 * @date 18/6/5
 */
public interface RpcSerializable extends Serializable{
    String getSerialize();

    void setSerialize(String serialize);

}

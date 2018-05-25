package code.library.serializer;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author fuqianzhong
 * @date 18/5/16
 * 序列化的接口类,定义待实现的方法
 */
public interface Serializer {
    void serialize(Object obj,OutputStream os);

    Object deserialize(InputStream is);
}

package code.library.serializer;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author fuqianzhong
 * @date 18/5/16
 * Hessian 序列化,相对于java序列化,有几个优点,
 * 1. 性能更佳 2. 占用空间更小
 * 适合rpc中的序列化层
 */
public class HessianSerializer implements Serializer {
    @Override
    public void serialize(Object obj, OutputStream os) {
        try {
            Hessian2Output ho = new Hessian2Output(os);
            try {
                ho.writeObject(obj);
                ho.flush();
            } finally {
                ho.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object deserialize(InputStream is) {
        try {
            Hessian2Input hi = new Hessian2Input(is);
            try {
                return hi.readObject();
            } finally {
                hi.close();
            }
        } catch (Throwable t) {
            return null;
        }
    }
}

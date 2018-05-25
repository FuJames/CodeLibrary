package code.library.serializer;

import java.io.*;

/**
 * @author fuqianzhong
 * @date 18/5/16
 * java序列化类
 * 1. 序列化: 将对象转化为二进制字节流
 * 2. 反序列化: 将二进制字节流,转化为java对象
 * 3. 被序列化的java对象,必须实现Serializable接口,否则会报NotSerializableException错误
 */
public class JavaSerializer implements Serializer{
    @Override
    public void serialize(Object obj,OutputStream os){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(os);
            try {
                oos.writeObject(obj);
                oos.flush();
            } finally {
                oos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object deserialize(InputStream is) {
        try {
            ObjectInputStream ois = new ObjectInputStream(is);
            try {
                return ois.readObject();
            } finally {
                ois.close();
            }
        } catch (Throwable t) {
            return null;
        }
    }

}

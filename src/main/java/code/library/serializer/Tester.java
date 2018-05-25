package code.library.serializer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author fuqianzhong
 * @date 18/5/16
 */
public class Tester {

    public static void main(String[] args) throws FileNotFoundException {
        //将二级制字节流写入文件
        FileOutputStream fos = new FileOutputStream("obj.out");
        UserDto userDto =  new UserDto();
        userDto.setAge(20);
        userDto.setName("fqz");
        userDto.setAddress("世界之巅");
        JavaSerializer javaSerializer = (JavaSerializer) SerializerFactory.getSerializer(SerializerType.JAVA);
        javaSerializer.serialize(userDto, fos);

        //从文件读取二进制字节流,并反序列化成java对象
        FileInputStream fis = new FileInputStream("obj.out");
        UserDto userDto2 = (UserDto) javaSerializer.deserialize(fis);
        System.out.println(userDto2);

        fos = new FileOutputStream("obj.out");
        HessianSerializer hessianSerializer = (HessianSerializer) SerializerFactory.getSerializer(SerializerType.HESSIAN);
        hessianSerializer.serialize(userDto, fos);

        fis = new FileInputStream("obj.out");
        userDto2 = (UserDto) hessianSerializer.deserialize(fis);
        System.out.println(userDto2);

    }
}

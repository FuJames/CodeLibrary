package code.library.serializer;

import code.library.common.dto.UserDTO;

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
        UserDTO userDto =  new UserDTO();
        userDto.setAge(20);
        userDto.setName("fqz");
        userDto.setAddress("世界之巅");
        JavaSerializer javaSerializer = (JavaSerializer) SerializerFactory.getSerializer(SerializerType.JAVA);
//        javaSerializer.serialize(userDto, fos);
        //将二进制字节流转化成字节数组
        byte[] bytes = javaSerializer.serialize(userDto);
        UserDTO userDTO = javaSerializer.deserialize(bytes, UserDTO.class);
        System.out.println(userDTO);
        //从文件读取二进制字节流,并反序列化成java对象
//        FileInputStream fis = new FileInputStream("obj.out");
//        UserDTO userDto2 = (UserDTO) javaSerializer.deserialize(fis);
//        System.out.println(userDto2);
//
//        fos = new FileOutputStream("obj.out");
        HessianSerializer hessianSerializer = (HessianSerializer) SerializerFactory.getSerializer(SerializerType.HESSIAN);
        byte[] bytes2 = hessianSerializer.serialize(userDto);
        UserDTO userDto2 = hessianSerializer.deserialize(bytes2,UserDTO.class);
        System.out.println(userDto2);

        JacksonSerializer jacksonSerializer = new JacksonSerializer();
        byte[] bytes3 = jacksonSerializer.serialize(userDto);
        UserDTO userDTO3 = jacksonSerializer.deserialize(bytes3,UserDTO.class);
        System.out.println(userDTO3);

        ProtostuffSerializer protostuffSerializer = new ProtostuffSerializer();
        byte[] bytes4 = protostuffSerializer.serialize(userDto);
        UserDTO userDTO4 = protostuffSerializer.deserialize(bytes4,UserDTO.class);
        System.out.println(userDTO4);
    }
}

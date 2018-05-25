package code.library.serializer;

import lombok.ToString;

import java.io.Serializable;

/**
 * @author fuqianzhong
 * @date 18/5/16
 */
@ToString
public class UserDto implements Serializable{
    private static final long serialVersionUID = -4539738559424380885L;

    private int age;

    private String name;

    private String address;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

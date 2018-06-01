package code.library.common.dto;

import lombok.ToString;

import java.io.Serializable;

/**
 * Created by fuqianzhong on 17/9/22.
 */
@ToString
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -3652431661699986732L;

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

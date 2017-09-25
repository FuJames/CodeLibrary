package code.library.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by fuqianzhong on 17/9/22.
 */
@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -3652431661699986732L;

    private Integer id;
    private String name;
    private Boolean sex;/*1:男,0:女*/
    private String mobile;
}

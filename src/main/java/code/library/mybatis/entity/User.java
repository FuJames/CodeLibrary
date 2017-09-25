package code.library.mybatis.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by fuqianzhong on 17/9/22.
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 8679917513847109776L;

    private Integer id;
    private String name;
    private Boolean sex;
    private String mobile;
}

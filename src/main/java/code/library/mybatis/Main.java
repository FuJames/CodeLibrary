package code.library.mybatis;

import code.library.mybatis.useSqlSession.SqlOperation;

/**
 * Created by fuqianzhong on 17/9/22.
 */
public class Main {
    public static void main(String[] args) {
        SqlOperation sqlOperation = new SqlOperation();
        sqlOperation.addUser();

    }
}

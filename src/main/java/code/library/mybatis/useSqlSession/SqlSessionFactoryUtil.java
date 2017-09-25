package code.library.mybatis.useSqlSession;

/**
 * Created by fuqianzhong on 17/9/22.
 * 创建sqlsessionFactory
 */

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * 1. 创建SqlSessionFactory,通过此类可以创建SqlSession,SqlSession是与数据库交互的操作类
 */
public class SqlSessionFactoryUtil {

    private final static SqlSessionFactory sqlSessionFactory;

    static{
        String resource = "mybatis/useSqlSession/mybatis-config.xml";
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    public static SqlSessionFactory getSqlsessionfactory() {
        return sqlSessionFactory;
    }

}

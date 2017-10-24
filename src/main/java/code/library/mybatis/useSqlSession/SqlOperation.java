package code.library.mybatis.useSqlSession;

import code.library.mybatis.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Created by fuqianzhong on 17/9/22.
 */
public class SqlOperation {
    private static SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlsessionfactory();

    public void addUser(){
        User user = new User();
        user.setName("King James");
        user.setMobile("18866668888");
        user.setSex(true);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            //statement对应的是mapper.xml中的namespace + sqlId
//            sqlSession.insert("code.library.mybatis.useMapper.UserMapper.insertUser",user);
            User userSelected = sqlSession.selectOne("code.library.mybatis.useMapper.UserMapper.selectById",91);
            sqlSession.commit();
            sqlSession.selectOne("code.library.mybatis.useMapper.UserMapper.selectById",91);
            System.out.println(userSelected);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

}

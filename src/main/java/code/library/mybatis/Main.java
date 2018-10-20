package code.library.mybatis;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by fuqianzhong on 17/9/22.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
//        SqlOperation sqlOperation = new SqlOperation();
//        sqlOperation.addUser();
        List<Object> row = Lists.newArrayList();
        row.add(null);
        row.add(null);
        System.out.println(row);

        logger.info("sb");
    }
}

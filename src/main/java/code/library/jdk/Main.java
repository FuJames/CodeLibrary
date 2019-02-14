package code.library.jdk;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @author fuqianzhong
 * @date 19/1/16
 */
public class Main {
    public static void main(String[] args) throws Exception {
        List<Integer> list1 = Lists.newArrayList(1,2,3,4,5);
        List<Integer> list2 = Lists.newArrayList(1,4,5,6,7);
        System.out.println(CollectionUtils.intersection(list1,list2));//交集
        System.out.println(CollectionUtils.union(list1,list2));//并集
        System.out.println(CollectionUtils.subtract(list1,list2));//差集

//        testWaitTimeOut();
    }

    private static void testWaitTimeOut() throws InterruptedException {
        Object lock = new Object();
        Thread threadA = new Thread(new RunA(lock));
        threadA.start();

        Thread threadB = new Thread(new RunB(lock));
        threadB.start();
    }
}

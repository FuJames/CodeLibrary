package code.library.jdk;

/**
 * @author fuqianzhong
 * @date 19/1/16
 */
public class Main {
    public static void main(String[] args) throws Exception {
        testWaitTimeOut();
    }

    private static void testWaitTimeOut() throws InterruptedException {
        Object lock = new Object();
        Thread threadA = new Thread(new RunA(lock));
        threadA.start();

        Thread threadB = new Thread(new RunB(lock));
        threadB.start();
    }
}

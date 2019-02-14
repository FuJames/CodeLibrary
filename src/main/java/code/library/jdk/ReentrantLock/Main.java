package code.library.jdk.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fuqianzhong
 * @date 2019/1/23
 */
public class Main {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Thread lockThread = new Thread(new LockRunner(lock));
        lockThread.setName("lockThread");

        Thread tryLockThread = new Thread(new TryLockRunner(lock));
        tryLockThread.setName("tryLockThread");

        Thread tryLockTimeoutThread = new Thread(new TryLockTimeoutRunner(lock));
        tryLockTimeoutThread.setName("tryLockTimeoutThread");

        Thread lockInterruptiblyThread = new Thread(new LockInterruptiblyRunner(lock));
        lockInterruptiblyThread.setName("lockInterruptiblyThread");

        lockThread.start();
//        tryLockThread.start();
//        tryLockTimeoutThread.start();
        lockInterruptiblyThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lockInterruptiblyThread.interrupt();
    }
}

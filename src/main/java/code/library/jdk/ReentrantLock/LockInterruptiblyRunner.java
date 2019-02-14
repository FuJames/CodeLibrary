package code.library.jdk.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fuqianzhong
 * @date 2019/1/23
 */
public class LockInterruptiblyRunner implements Runnable {
    private ReentrantLock lock;

    public LockInterruptiblyRunner(ReentrantLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interruptibly success ...");
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}

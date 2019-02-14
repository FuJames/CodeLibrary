package code.library.jdk.ReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fuqianzhong
 * @date 2019/1/23
 */
public class TryLockTimeoutRunner implements Runnable {
    private ReentrantLock lock;

    public TryLockTimeoutRunner(ReentrantLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(Thread.currentThread().getName() + " try lock ...");

            boolean hasLock = lock.tryLock(2000, TimeUnit.MILLISECONDS);
            if(hasLock){
                try {
                    System.out.println(Thread.currentThread().getName() + " acquire lock ...");
                } finally {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + " release lock ...");
                }
            }else {
                System.out.println(Thread.currentThread().getName() + " acquire lock fail ...");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

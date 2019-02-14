package code.library.jdk.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fuqianzhong
 * @date 2019/1/23
 */
public class LockRunner implements Runnable {
    private ReentrantLock lock;

    public LockRunner(ReentrantLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " acquire lock ...");
            int i = 0;
            while (i++ < 5){
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " release lock ...");
        }
    }
}

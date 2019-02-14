package code.library.jdk.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fuqianzhong
 * @date 2019/1/23

 */
public class TryLockRunner implements Runnable{

    private ReentrantLock lock;

    public TryLockRunner(ReentrantLock lock) {
        this.lock = lock;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean hasLock = lock.tryLock();
        if(hasLock){
            try {
                System.out.println(Thread.currentThread().getName() + " tryLock success ...");
            } finally {
                lock.unlock();
            }
        }else {
            System.out.println(Thread.currentThread().getName() + " tryLock fail ...");
        }
    }
}

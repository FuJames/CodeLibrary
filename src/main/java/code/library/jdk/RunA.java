package code.library.jdk;

/**
 * @author fuqianzhong
 * @date 19/1/16
 */
public class RunA implements Runnable {

    private Object lock;

    public RunA(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {

        synchronized (lock){
            try  {
                System.out.println("A wait lock");
                // lock.wait(); // 释放锁并永远等待着，直到notify或notifyAll
                lock.wait(2000);// 等待了2秒后，重新申请lock锁,申请到锁或notify/notifyAll,后再继续执行下去
                System.out.println("A end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

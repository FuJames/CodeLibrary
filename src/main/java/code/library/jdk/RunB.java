package code.library.jdk;

/**
 * @author fuqianzhong
 * @date 19/1/16
 */
public class RunB implements Runnable {

    private Object lock;

    public RunB(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {

        synchronized (lock) {
            System.out.println("b has lock");
            int i = 0;
            boolean flag = true;
//            while (flag) {
//                System.out.println("b work " + i +" times");
//
//                if(i++ > 1000){
//                    System.out.println("b release lock");
//                    lock.notify();
//                    flag = false;
//                }
//            }
            while (true){//b不释放锁,A将永远无法运行下去
            }
        }
    }
}

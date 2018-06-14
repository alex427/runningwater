package alex.thread_features.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhiguang on 2018/4/29.
 */
public class DeadLockDemo {
    //死锁: 相互持有对方正在等待的锁, 而且不释放对方正在等待的锁
    private static final Lock lock1 = new ReentrantLock();
    private static final Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 500; i++) {
                    synchronized (lock1) {
                        synchronized (lock2) {
                            System.out.println("aaaaaa");
                        }
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 500; i++) {
                    synchronized (lock2) {
                        synchronized (lock1) {
                            System.out.println("bbbbbbb");
                        }
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}

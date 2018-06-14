package alex.thread_features.reentrantlockk;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhiguang on 2018/4/29.
 */
public class ThreadDemo8 {
    public static void main(String[] args) {
        final MyService4 ms = new MyService4();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 110; i++) {
                    try {
                        ms.work1();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 110; i++) {
                    try {
                        ms.work2();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 110; i++) {
                    try {
                        ms.work3();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

class MyService4 {
    private volatile int flag = 0;
    //定义锁和条件
    //锁只有一把, 作用于lock和unlock区域; 条件是三个, 捆绑到对应的线程, 定向等待, 定向唤醒
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void work1() throws InterruptedException {
        lock.lock();
        //因为是定向唤醒目标线程, 所以无需while判断, 节省资源
            if (flag != 0) {
                c1.await();
            }
            System.out.print("Hello ");
            System.out.print("world ");
            System.out.println(" ...... ");
            flag = 1;
            c2.signal();
        lock.unlock();
    }


    public void work2() throws InterruptedException {
        lock.lock();
            if (flag != 1) {
                c2.await();
            }
            System.out.print("Welcome, ");
            System.out.print("you guys  ");
            System.out.println(" !!!! ");
            flag = 2;
            c3.signal();
        lock.unlock();
    }

    public void work3() throws InterruptedException {
        lock.lock();
            if (flag !=2) {
                c3.await();
            }
            System.out.print("work, ");
            System.out.print("you stupid  ");
            System.out.println(" !!!! ");
            System.out.println(" -------------------- ");
            flag = 0;
            c1.signal();
        lock.unlock();
    }
}

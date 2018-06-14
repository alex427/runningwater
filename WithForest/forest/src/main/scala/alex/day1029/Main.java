package alex.day1029;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhiguang on 2017/10/29.
 */
public class Main {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    volatile private static int flag = 1;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                lock.lock();
                try {
                    while (flag != 1) {
                        condition.await();
                    }
                    System.out.println("get lock");
                    for (int i = 0; i < 3; i++) {
                        System.out.println(Thread.currentThread().getName() + "  " + i);
                        Thread.sleep(500);
                    }
                    flag = 2;
                    condition.signalAll();
                    System.out.println("send signals..");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "t1").start();


        new Thread(new Runnable() {
            public void run() {
                lock.lock();
                try {
                    while (flag != 2) {
                        condition.await();
                    }
                    System.out.println("get lock");
                    for (int i = 0; i < 3; i++) {
                        System.out.println(Thread.currentThread().getName() + "  " + i);
                        Thread.sleep(500);
                    }
                    flag = 3;
                    condition.signalAll();
                    System.out.println("send signals..");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "t2").start();

        new Thread(new Runnable() {
            public void run() {
                lock.lock();
                try {
                    while (flag != 3) {
                        condition.await();
                    }
                    System.out.println("get lock");
                    for (int i = 0; i < 3; i++) {
                        System.out.println(Thread.currentThread().getName() + "  " + i);
                        Thread.sleep(500);
                    }
                    flag = 1;
                    condition.signalAll();
                    System.out.println("send signals..");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "t3").start();
    }

}






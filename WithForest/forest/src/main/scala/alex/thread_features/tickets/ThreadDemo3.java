package alex.thread_features.tickets;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhiguang on 2018/4/29.
 */
public class ThreadDemo3 {
    private static int ticket = 400000;

    public static void main(String[] args) {

        for (int i = 0; i < 500; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //synchronized (ThreadDemo3.class) {    不可这里加锁
                    while (ticket > 0) {
                        synchronized (ThreadDemo3.class) {
                            if (ticket <= 0) {
                                break;
                            }
                            ticket = ticket - 1;
                            System.out.println(Thread.currentThread().getName() + "   售出票号:   " + (ticket + 1));

                            try {
                                //Thread.sleep(10);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            },("窗口"+i)).start();
        }
    }
}

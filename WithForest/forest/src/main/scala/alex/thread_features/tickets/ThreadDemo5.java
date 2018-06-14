package alex.thread_features.tickets;

/**
 * Created by zhiguang on 2018/4/29.
 */
public class ThreadDemo5 {
    public static void main(String[] args) {

        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //这里加锁, 锁里面有一个死循环, 程序变成单线程
                    synchronized (ThreadDemo3.class) {
                        while (true) {
                            System.out.println(Thread.currentThread().getName() );
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }).start();
        }
    }
}

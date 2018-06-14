package alex.day1026.notifyDemo;

/**
 * Created by zhiguang on 2017/10/26.
 */
public class Service {
    public void work(Object lock) throws InterruptedException {
        synchronized (lock){
            System.out.println(Thread.currentThread().getName()+" 开始wait");
            lock.wait();
            System.out.println(Thread.currentThread().getName() + " 被唤醒....");
        }
    }
}

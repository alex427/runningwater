package alex.day1023.DeadLock;

/**
 * Created by zhiguang on 2017/10/23.
 */
public class Service {
    private int a;
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public synchronized void methodA(int a){
        while(true){
            synchronized (lock1) {
                System.out.println("持有lock1, 需要lock2");
                //方法还没执行完成, 所以没有释放lock1, 继续执行, 需要lock2
                //今天发现自己把释放锁和交出CPU执行权弄混了
                //sleep是交出CPU执行权, 但是跟释放锁没有关系
                synchronized (lock2) {
                    System.out.println("获得lock2");
                    System.out.println("业务逻辑......");
                }
            }
        }
    }


    public void methodB(int a) {
        while(true){
            synchronized (lock2) {
                System.out.println("持有lock2, 需要lock1");
                //方法还没执行完成, 所以没有释放lock2, 继续执行, 需要lock1
                synchronized (lock1) {
                    System.out.println("获得lock1");
                    System.out.println("业务逻辑......");
                }
            }
        }
    }
}

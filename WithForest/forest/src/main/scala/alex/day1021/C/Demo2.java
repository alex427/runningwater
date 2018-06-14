package alex.day1021.C;

/**
 * Created by zhiguang on 2017/10/21.
 */
public class Demo2 {
    
    public static void main(String[] args) throws InterruptedException {
        //MyThread mt = new MyThread();
        //mt.start();
       // Thread.sleep(100);
       // mt.interrupt();

        MyThread2 mt2 = new MyThread2();
        mt2.start();
        Thread.sleep(7);
        mt2.interrupt();
    }
}

package alex.day1021.B;

/**
 * Created by zhiguang on 2017/10/21.
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(this.toString() +"  "+ this.isAlive());
        System.out.println(Thread.currentThread().toString() +"  "+ Thread.currentThread().isAlive());
    }
}

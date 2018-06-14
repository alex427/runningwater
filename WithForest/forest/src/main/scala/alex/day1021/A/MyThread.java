package alex.day1021.A;

/**
 * Created by zhiguang on 2017/10/21.
 */
public class MyThread extends Thread{
    @Override
    public void run(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }
       System.out.println("开始运行时:  "+ this.isAlive());
    }
}

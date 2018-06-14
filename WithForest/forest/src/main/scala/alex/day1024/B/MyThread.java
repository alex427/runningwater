package alex.day1024.B;

/**
 * Created by zhiguang on 2017/10/24.
 */
public class MyThread extends Thread{
    //volatile不具备原子性, 不能用它来保证原子操作
    //volatile public static int counter = 0;
     public static int counter = 0;

    //synchronized才具备原子性
    //public  static void addCount(){
    public synchronized static void addCount(){
        for (int i=0;i<100;i++){
            counter++;
            System.out.println(Thread.currentThread().getName()+"    "+counter);
        }
    }

    @Override
    public void run(){
        addCount();
    }
}

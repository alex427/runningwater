package alex.day1021.B;

/**
 * Created by zhiguang on 2017/10/21.
 * Thread.currentThread 和 this的差别
 */
public class Demo1 {
    
    public static void main(String[] args){
        MyThread mt = new MyThread();
        mt.setName("t1");
        Thread th = new Thread(mt);
        th.setName("t2");
        th.start();  // 此时两者不同
        //mt.start();  //此时两者相同
    }
}

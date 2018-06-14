package alex.day1021.A;

/**
 * Created by zhiguang on 2017/10/21.
 */
public class Demo1 {
    
    public static void main(String[] args){
        MyThread mt = new MyThread();
        System.out.println("还没开始 "+mt.isAlive());

        Thread th = new Thread(mt,"a");
        th.start();
        //mt.start();

        System.out.println("正在运行 "+mt.isAlive());
    }
}

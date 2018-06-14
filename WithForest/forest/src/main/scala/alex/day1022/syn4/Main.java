package alex.day1022.syn4;

/**
 * Created by zhiguang on 2017/10/23.
 */
public class Main {
    public static void main(String[] args){
        MyService service = new MyService();
        MyLock lock = new MyLock();

        Thread th = new ThreadF(service,lock);
        Thread th2 = new ThreadF(service,lock);
        th.start();
        th2.start();
    }
}

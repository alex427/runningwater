package alex.day1023.DeadLock;

/**
 * Created by zhiguang on 2017/10/23.
 */
public class Main {

    public static void main(String[] args){
        Service service = new Service();
        Thread t1 = new ThreadA(service);
        Thread t2 = new ThreadB(service);
        t1.start();
        t2.start();
    }
}

package alex.day1023.volatiledemo;

/**
 * Created by zhiguang on 2017/10/24.
 */
public class Main {
    public static void main(String[] args){
        Service service = new Service();
        Thread t1 = new ThreadA(service);
        t1.start();
        //service.flag=true;
    }

}

package alex.day1023.volatiledemo;

/**
 * Created by zhiguang on 2017/10/24.
 */
public class ThreadA extends Thread{
    Service service = new Service();

    public ThreadA(Service service){
        this.service=service;
    }
    @Override
    public  void run(){
        this.service.work();
    }
}

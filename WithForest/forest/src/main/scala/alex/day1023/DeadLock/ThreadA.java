package alex.day1023.DeadLock;

/**
 * Created by zhiguang on 2017/10/23.
 */
public class ThreadA extends  Thread{
    Service service ;

    public ThreadA(Service service){
       this.service=service;
    }
    @Override
    public  void run(){
        this.service.methodA(1);
    }
}

package alex.day1023.DeadLock;

/**
 * Created by zhiguang on 2017/10/23.
 */
public class ThreadB extends  Thread{
    Service service = new Service();

    public ThreadB(Service service){
       this.service=service;
    }
    @Override
    public  void run(){
        this.service.methodB(2);
    }
}

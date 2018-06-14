package alex.day1022.syn4;

/**
 * Created by zhiguang on 2017/10/23.
 */
public class ThreadF  extends Thread{
    private MyService service;
    private MyLock lock;

    public ThreadF(MyService service,MyLock lock){
        this.service=service;
        this.lock=lock;
    }

    @Override
    public void run(){
        try {
            this.service.doservice(this.lock);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

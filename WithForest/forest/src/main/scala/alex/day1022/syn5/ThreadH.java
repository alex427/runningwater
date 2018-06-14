package alex.day1022.syn5;


/**
 * Created by zhiguang on 2017/10/23.
 */
public class ThreadH extends Thread{
    private MyService service;
    private MyLock lock;

    public ThreadH(MyService service, MyLock lock){
        this.service=service;
        this.lock=lock;
    }

    @Override
    public void run(){
        try {
            this.service.doserviceC(this.lock);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

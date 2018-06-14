package alex.day1022.syn2;

import javax.xml.ws.Service;

/**
 * Created by zhiguang on 2017/10/22.
 */
public class ThreadA extends  Thread{
    private MyContainer mc;

    public ThreadA(MyContainer mc){
        this.mc = mc;
    }

    @Override
    public void run(){
        ServiceA service = new ServiceA();
        service.addData(this.mc, "th-a");
        System.out.println(service);
        System.out.println(this.mc);
    }
}

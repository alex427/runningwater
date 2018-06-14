package alex.day1018;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

/**
 * Created by zhiguang on 2017/10/18.
 */
public class Banji implements Runnable{

    private DelayQueue<Sudent> dq = new DelayQueue<Sudent>();

    public void enter(int id, String name, int score){
        Sudent sudent = new Sudent(id,name,score);
        this.dq.add(sudent);
    }

    public void pass(Sudent s){
        System.out.println(s.getName()+" pass ");
    }

    public void run() {
        for(;;){
            try {
                Sudent s = this.dq.take();
                pass(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        Banji b = new Banji();
        b.enter(1,"jack",100);
        b.enter(1,"mack",500);
        b.enter(1,"zack",1000);

        Thread th = new Thread(b);
        th.start();
    }

}

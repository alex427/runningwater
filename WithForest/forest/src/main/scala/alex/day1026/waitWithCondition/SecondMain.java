package alex.day1026.waitWithCondition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiguang on 2017/10/26.
 */
public class SecondMain {
    public static void main(String[] args){
        Object lock = new Object();
        Thread t1 = new ThreadAA(lock);
        Thread t2 = new ThreadBB(lock);
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();
    }
}


class Val{
    public static int value = 0;
}

class ThreadAA extends Thread{
    private Object lock;

    public ThreadAA(Object lock){
        this.lock=lock;
    }

    @Override
    public void run(){
        synchronized (lock){
            if(Val.value == 0){
                Val.value =1;
                lock.notify();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


class ThreadBB extends Thread{
    private Object lock;

    public ThreadBB(Object lock){
        this.lock=lock;
    }

    @Override
    public void run(){
        synchronized (lock){
            if(Val.value != 0){
                System.out.println(Val.value);
                lock.notify();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

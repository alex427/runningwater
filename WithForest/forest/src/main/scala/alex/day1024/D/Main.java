package alex.day1024.D;

import java.util.Objects;

/**
 * Created by zhiguang on 2017/10/24.
 * 两个线程采用wait和notify进行通信
 */
public class Main {
    public static void main(String[] args){
        MyContainer container = new MyContainer();
        Object obj = new Object();
        Thread t1 = new ThreadA(container,obj);
        Thread t2 = new ThreadB(container,obj);
        t1.start();
        t2.start();
    }
}

class ThreadA extends Thread{
    private MyContainer container;
    private Object lock;
    public ThreadA(MyContainer container, Object lock){
        this.container=container;
        this.lock = lock;
    }
    //线程A添加元素, 当容器达到5个时, 通知线程B, 等待线程B执行完成, 由线程B唤醒自己
    @Override
    public void run(){
        synchronized (lock) {
            for (int i = 0; i < 10; i++) {
                this.container.add("aaaa");
                System.out.println("aaaa" + (i+1));
                if(i==4){
                    System.out.println("notify...");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

class ThreadB extends Thread{
    private MyContainer container;
    private Object lock;
    public ThreadB(MyContainer container, Object lock){
        this.container=container;
        this.lock = lock;
    }
    //容器元素达到5个时, 线程A通知自己, 然后自己执行业务逻辑, 执行完成,通知线程A
    @Override
    public void run(){
        synchronized (lock) {
            try {
                if(this.container.size() !=5){
                    lock.wait();
                } else{
                    System.out.println(this.container.size());
                    Thread.sleep(1000);
                    lock.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
package alex.day1028.threadlocal;

/**
 * Created by zhiguang on 2017/10/28.
 */
public class Main {
    public static void main(String[] args){
        Tool.t.set("aaa");
        Thread t1 = new ThreadA();
        Thread t2 = new ThreadB();
        t1.start();
        t2.start();
        System.out.println(Thread.currentThread().getName()+"  "+Tool.t.get());
    }
}

class Tool  {
    public static ThreadLocal  t = new ThreadLocal ();
}

class ThreadA extends Thread{
    @Override
    public void run(){
        Tool.t.set("bbb");
        System.out.println(Thread.currentThread().getName()+"  "+Tool.t.get());
    }
}


class ThreadB extends Thread{
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName()+"  "+Tool.t.get());
    }
}
package alex.thread_features.notifyy;

/**
 * Created by zhiguang on 2018/4/29.
 */
public class ThreadDemo6 {
    public static void main(String[] args){
        final MyService2 ms = new MyService2();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<20;i++){
                    try {
                        ms.work1();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<20;i++){
                    try {
                        ms.work2();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

class MyService2 {
    private int flag=0;
    public void work1() throws InterruptedException {
        //不能用class来锁, 会出现监视器状态异常, 原因是什么?
        //因为flag是成员变量, 属于当前对象this, 而下面是用this.notify来唤醒, 两者不一致
       // synchronized (MyService2.class){
        synchronized (this) {  //this是线程中使用的对象, 在上面的代码中, 只创建了一个, 而且final, 所以可以用来加锁
            if(flag !=0){
                this.wait();
            }
            System.out.print("Hello ");
            System.out.print("world ");
            System.out.println(" ...... ");
            flag=1;
            this.notify();
        }
    }

    public void work2() throws InterruptedException {
        //synchronized (MyService2.class) {
        synchronized (this) {
            if(flag !=1){
                this.wait();
            }
            System.out.print("Welcome, ");
            System.out.print("you guys  ");
            System.out.println(" !!!! ");
            flag=0;
            this.notify();
        }
    }
}
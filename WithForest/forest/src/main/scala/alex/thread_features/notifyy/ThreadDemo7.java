package alex.thread_features.notifyy;

/**
 * Created by zhiguang on 2018/4/29.
 */
public class ThreadDemo7 {

    //notify 随机唤醒一个等待线程, notifyall 唤醒所有等待线程
    //当有3个或3个以上线程并发执行, 并且要进行通信, 采用notify是不行的,因为它仅仅随机唤醒一个等待线程
    //而这个被唤醒的线程, 很可能内部判断条件不满足, 继续wait; 那个条件判断满足的线程, 有没有被唤醒
    //导致大家都处于wait状态, 而程序整体等待.
    //这时就要采用notifyall, 全部唤醒, 你有判断条件满足, 你就执行.
    //即便如此, 这种机制并不好, JDK1.5后提供了互斥锁, 来解决这些问题

    public static void main(String[] args){
        final MyService3 ms = new MyService3();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<110;i++){
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
                for (int i=0;i<110;i++){
                    try {
                        ms.work2();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<110;i++){
                    try {
                        ms.work3();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

class MyService3 {
    private volatile int  flag=0;
    public void work1() throws InterruptedException {
        //不能用class来锁, 会出现监视器状态异常, 原因是什么?
        //因为flag是成员变量, 属于当前对象this, 是当前对象this的状态
        // synchronized (MyService2.class){
        synchronized (this) {  //this是线程中使用的对象, 在上面的代码中, 只创建了一个, 而且final, 所以可以用来加锁
            //这个地方要while来控制判断, 不能用if, 因为if只判断一次
            while(flag !=0){
                this.wait();
            }
            System.out.print("Hello ");
            System.out.print("world ");
            System.out.println(" ...... ");
            flag=1;
            //this.notify();
            this.notifyAll();
        }
    }

    public void work2() throws InterruptedException {
        //synchronized (MyService2.class) {
        synchronized (this) {
            while(flag !=1){
                this.wait();
            }
            System.out.print("Welcome, ");
            System.out.print("you guys  ");
            System.out.println(" !!!! ");
            flag=2;
            //this.notify();
            this.notifyAll();
        }
    }

    public void work3() throws InterruptedException {
        synchronized (this) {
            while(flag !=2){
                this.wait();
            }
            System.out.print("work, ");
            System.out.print("you stupid  ");
            System.out.println(" !!!! ");
            System.out.println(" -------------------- ");
            flag=0;
            //this.notify();
            this.notifyAll();
        }
    }
}

package alex.day1026.notifyallDemo;

/**
 * Created by zhiguang on 2017/10/26.
 */
public class Main {
    public static void main(String[] args) {
        //三个线程的service方法不是同一个, 都是独自new的, 这有区别吗?
        //无区别
        //service只是你抽取的业务方法, 完全可以不要单独的service类, 而把代码写在各个线程内部, 这一点不要疑惑.
        Object lock = new Object();
        Thread t1 = new ThreadA(lock);
        Thread t2 = new ThreadB(lock);
        Service service = new Service();
        Thread t3 = new ThreadC(service,lock);
        Thread t4 = new ThreadD(lock);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

class ThreadA extends Thread {
    private Object lock;

    public ThreadA(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        Service service = new Service();
        try {
            service.work(this.lock);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadB extends Thread {
    private Object lock;

    public ThreadB(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        Service service = new Service();
        try {
            service.work(this.lock);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadC extends Thread {
    private Object lock;
    private Service service;

    public ThreadC(Service service, Object lock) {
        this.service = service;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            this.service.work(this.lock);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadD extends Thread {
    private Object lock;

    public ThreadD(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            this.lock.notifyAll(); //一次唤醒全部
            System.out.println("全部唤醒, 等待的线程获取锁, 开始工作...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }
}
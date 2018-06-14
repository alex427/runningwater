package alex.day1024.A;

/**
 * Created by zhiguangai on 2017/10/24.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        StringWorker worker = new StringWorker();
        Thread t1 = new ThreadA(worker);
        t1.start();
        //线程已经启动, 进入死循环, 程序希望将flag设置为true, 跳出死循环
        Thread.sleep(200); //模拟业务花销时间. 必须有,否则程序会在t1启动前, 执行到这里, 就看不到效果了
        worker.setFlag(true);
        //原因是线程启动后就会在自己的私有堆栈中读取flag, 而 worker.setFlag(true) 是在公共堆栈中进行修改, 后者无法获取到修改后的数据
        //这就是需要使用volatile的原因, volatile关键字强制线程去公共堆栈获取数据, 保证了数据的更新在线程间可见.
    }
}

class ThreadA extends Thread {
    private StringWorker worker;
    public ThreadA(StringWorker worker){
        this.worker=worker;
    }
    @Override
    public void run(){
        this.worker.work();
    }
}

class ThreadB extends Thread {
    private StringWorker worker;
    public ThreadB(StringWorker worker){
        this.worker=worker;
    }
    @Override
    public void run(){
        this.worker.work();
    }
}
package alex.day1015;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhiguang on 2017/10/15.
 * 自己实现一个Queue
 */
public class MyQueue {
    //容器
    private final LinkedList<Object> list = new LinkedList();
    //容器的下限
    private final int minsize = 0;
    //容器的上限
    private  int maxsize;
    //计数器
    private AtomicInteger counter = new AtomicInteger(0);
    //锁
    private Object lock = new Object();

    public MyQueue(int maxsize){
        this.maxsize = maxsize;
    }

    //put
    public Object put(Object obj){
        synchronized (lock){
            //如果满了, 就阻塞
            while(this.counter.get() == maxsize){
                try {
                    System.out.println("满了, 等会儿");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //如没满, 执行添加
            this.list.addLast(obj);
            //计数器递增
            this.counter.incrementAndGet();
            //唤醒
            lock.notify();
            return obj;
        }
    }

    //take
    public Object take(){
        Object res = null;
        synchronized (lock){
            //如果达到下限, 阻塞
            while(this.counter.get() == minsize){
                try {
                    System.out.println("空的, 等会儿");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //如果没有达到下限,执行take
            res = this.list.removeFirst();
            //计数器递减
            counter.decrementAndGet();
            //唤醒
            lock.notify();
            return res;
        }
    }
}

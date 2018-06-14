package alex.day1026.waitWithCondition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiguang on 2017/10/26.
 */
public class Main {
    public static void main(String[] args){
        Object lock = new Object();
        List<String> list = new ArrayList<String>();
        Thread t1 = new ThreadA(lock,list);
        Thread t2 = new ThreadB(lock,list);
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();
    }
}

class ThreadA extends Thread{
    private Object lock;
    private List<String> list;
    public ThreadA(Object lock,List<String> list){
        this.lock=lock;
        this.list=list;
    }
    //�߳�Aִ����Ӳ���, ÿ�����һ��Ԫ��, ��֪ͨ�߳�Bȥ��ӡ���
    //��ʵ�����������������������
    @Override
    public void run(){
        synchronized (lock){
            for(int i = 0;i<10;i++){
                System.out.println("start add..."+"a"+(i+1));
                this.list.add("a" + (i + 1));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.notify();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class ThreadB extends Thread{
    private Object lock;
    private List<String> list;
    public ThreadB(Object lock,List<String> list){
        this.lock=lock;
        this.list=list;
    }
    //�߳�Bȥ��ӡ���, Ȼ��֪ͨ�߳�A�������
    @Override
    public void run(){
        synchronized (lock){
            while (true) {
                if (this.list.size() == 0) {
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    for (int i = 0; i < 10; i++) {
                        System.out.println(this.list.get(i));
                        lock.notify();
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
}
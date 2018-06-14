package alex.day1028.MultiConsumer;

import java.util.LinkedList;

/**
 * Created by zhiguang on 2017/10/28.
 * ������������жϲ�����if, ����while, Ϊʲô???
 */
public class MyStack2<T> {
    private LinkedList<T> list = new LinkedList<T>();

    //push
    //�����������ܳ���2��
    synchronized public void push(T t) {
        while (this.list.size() >= 2) {
            try {
                notifyAll(); //���������߳�
                System.out.println(Thread.currentThread().getName() + " I am waiting to add..");
                Thread.sleep(1000);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.list.addFirst(t);
        System.out.println("element added ..");
    }

    //pop
    synchronized public T pop() {
        while (this.list.size() == 0) {
            try {
                notifyAll();
                System.out.println(Thread.currentThread().getName() + " I am waiting to consume..");
                Thread.sleep(1000);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        T t = this.list.removeFirst();
        System.out.println(Thread.currentThread().getName() + " is going to pop, list remain " + this.list.size() + "element");
        return t;

    }
}

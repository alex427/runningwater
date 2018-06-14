package alex.day1028.MultiConsumer;

import java.util.LinkedList;

/**
 * Created by zhiguang on 2017/10/28.
 */
public class MyStack <T>{
    private LinkedList<T> list = new LinkedList<T>();

    //push
    synchronized public void push(T t){
        this.list.addFirst(t);
    }

    //pop
    synchronized public T pop(){
        if(this.list.size() == 0){
            return null;
        } else {
            T t = this.list.removeFirst();
            return t;
        }
    }
}

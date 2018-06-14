package alex.day1022.syn3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiguang on 2017/10/22.
 * �Զ����̰߳�ȫ������
 */
public class MyContainer<T> {
    private List<T> list = new ArrayList<T>();

    public synchronized void add(T t){
        this.list.add(t);
    }

    public synchronized int size(){
        return this.list.size();
    }
}

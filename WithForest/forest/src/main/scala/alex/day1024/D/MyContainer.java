package alex.day1024.D;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiguang on 2017/10/24.
 */
public class MyContainer <T>{
    private List<T> list = new ArrayList<T>();

    public void add(T t){
        this.list.add(t);
    }

    public int size(){
       return this.list.size();
    }
}

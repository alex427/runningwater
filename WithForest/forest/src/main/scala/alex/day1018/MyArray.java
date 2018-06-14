package alex.day1018;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by zhiguang on 2017/10/18.
 */
public class MyArray<T extends Comparable> {

    private List<T> list = new ArrayList<T>();

    public int size(){
        return this.list.size();
    }

    public synchronized void put(T tt) {
        if (this.list.size() == 0) {
            this.list.add(tt);
        } else {
            ListIterator lt = list.listIterator();
            for(int i=0;i<list.size();i++){
                if(list.get(i).compareTo(tt) !=0){
                    list.add(tt);
                }
            }
        }
    }

    public static void main(String[] args){
        Person p1 = new Person("jack", 1);
        Person p2 = new Person("mack", 2);
        Person p3 = new Person("zack", 3);
        Person p4 = new Person("mark", 1);

        MyArray arr = new MyArray();
        arr.put(p1);
        arr.put(p2);
        arr.put(p3);
        arr.put(p4);
        arr.put(p2);
        System.out.println(arr.size());
        for(Object obj: arr.list){
            Person p = (Person)obj;
            System.out.println(p);
        }
    }
}

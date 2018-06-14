package alex.day1018;

/**
 * Created by zhiguang on 2017/10/18.
 */
public class Person implements Comparable{
    private String name;
    private int id;

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int compareTo(Object o) {
        Person p = (Person)o;
        return this.id-p.id;
    }

    public String toString(){
        return this.name;
    }
}

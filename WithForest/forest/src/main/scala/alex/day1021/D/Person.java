package alex.day1021.D;

/**
 * Created by zhiguang on 2017/10/21.
 */
public class Person {
    private String name;
    private int age;
    public Person(String name, int age){
        this.age=age;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public synchronized void work(){
        for (int i = 0; i < 10000; i++) {
            System.out.println(Thread.currentThread().getName()+"  "+this.getName()+"  "+this.getAge());
        }
    }
}

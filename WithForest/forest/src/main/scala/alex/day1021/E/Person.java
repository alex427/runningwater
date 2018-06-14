package alex.day1021.E;

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

    //��ͬ������
    public  void work(){
        if(this.getName().equals("jack")){
            System.out.println("sensitive words found, going to suspend");
            Thread.currentThread().suspend();
        } else {
            System.out.println(this.getName()+ "  "+ this.getAge());
        }
    }
}

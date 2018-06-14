package alex.day1022.syn1;

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

    //unsafework方法处理了person类的实例变量, 在多线程中存在线程不安全的隐患
    public  void unsafework(String aa){
        for (int i = 0; i < 200; i++) {
            if(aa.equals("aaa")) {
                age = 200;
                name="mack";
                System.out.println(aa+"  "+age+"  "+Thread.currentThread().getName() + "  " + this.getName() + "  " + this.getAge() + "  " + this.getName() + "  " + this.getAge() + "  " + this.getName() + "  " + this.getAge());
            } else {
                age = 33300;
                name="zack";
                System.out.println(aa + "  "+age+"  "+Thread.currentThread().getName() + "  " + this.getName() + "  " + this.getName() + "  " + this.getName() + "  " + this.getAge());
            }
            }
    }

    //safework处理的是局部变量, 没有实例变量, 局部变量在多线程之间不能被共享, 它是安全的
    public void safework(int i){
        if(i == 10){
            System.out.println("传入了10");
        } else{
            System.out.println(i++);
        }
    }

    public synchronized  void synworkA(){
        for (int i = 0; i < 200; i++) {
            System.out.println("I am A");
        }
    }

    public synchronized  void synworkB(){
        for (int i = 0; i < 200; i++) {
            System.out.println("I am B");
        }
    }
}

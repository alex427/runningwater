package alex.day1021.E;

/**
 * Created by zhiguang on 2017/10/21.
 */
public class Demo4 {
    public static void main(String[] args){
        Person person = new Person("jack",23);
        MyThread4 t4 = new MyThread4(person);
        MyThread5 t5 = new MyThread5(person);

        t4.start();
        t5.start();
    }
}

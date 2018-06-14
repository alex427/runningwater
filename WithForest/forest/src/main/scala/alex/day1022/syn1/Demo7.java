package alex.day1022.syn1;

/**
 * Created by zhiguang on 2017/10/22.
 */
public class Demo7 {
    public static void main(String[] args) throws InterruptedException {
        Person  person = new Person("jack",22);
        Person  person2 = new Person("jackkkkkkkkkkkk",2222222);
        Thread t1 = new MyThred6(person);
        Thread t2 = new MyThred6(person);
        t1.start();
        t2.start();

        Thread.sleep(2000);
        for (int i =0;i<5;i++) {
            Thread t3 = new MyThred7(person);
            Thread t4 = new MyThred8(person2);

            //t4.start();
            //t3.start();
        }

        Thread t7 = new MyThred9(person);
        Thread t8 = new MyThred10(person);
        t7.start();
        t8.start();
    }
}

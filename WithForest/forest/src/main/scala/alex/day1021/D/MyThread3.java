package alex.day1021.D;

/**
 * Created by zhiguang on 2017/10/21.
 */
public class MyThread3 extends Thread {
    private Person person;

    public MyThread3(Person person){
        this.person = person;
    }
    @Override
    public void run(){
        person.work();
    }
}

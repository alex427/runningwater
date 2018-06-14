package alex.day1021.E;

/**
 * Created by zhiguang on 2017/10/21.
 */
public class MyThread4 extends Thread{
    private Person person;

    public MyThread4(Person person){
        this.person = person;
    }
    @Override
    public void run(){
        this.person.work();
    }
}

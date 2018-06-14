package alex.day1021.E;

/**
 * Created by zhiguang on 2017/10/21.
 */
public class MyThread5 extends Thread{
    private Person person;

    public MyThread5(Person person){
        this.person = person;
    }

    @Override
    public void run(){
        this.person.setName("mack");
        this.person.setAge(25);
        this.person.work();
    }
}

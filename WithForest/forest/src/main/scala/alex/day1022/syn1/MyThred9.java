package alex.day1022.syn1;

/**
 * Created by zhiguang on 2017/10/22.
 */
public class MyThred9 extends Thread {
    private Person person;

    public MyThred9(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        this.person.synworkA();
    }
}

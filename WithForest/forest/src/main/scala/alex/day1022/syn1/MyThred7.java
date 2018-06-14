package alex.day1022.syn1;

/**
 * Created by zhiguang on 2017/10/22.
 */
public class MyThred7 extends Thread{
    private Person person;

    public MyThred7(Person person){
        this.person = person;
    }

    @Override
    public void run(){
        for (int i =0;i<15;i++) {
            this.person.unsafework("aad");
        }
    }
}

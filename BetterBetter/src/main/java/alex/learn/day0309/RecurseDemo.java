package day0309;

/**
 * Created by zhiguang on 2018/3/9.
 */
public class RecurseDemo {

    public static void main(String[] args) throws InterruptedException {
        RecurseDemo rd = new RecurseDemo();
        rd.work();
    }

    public int work() throws InterruptedException {
       int a = work();
        return a;
    }
}

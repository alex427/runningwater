package alex.day1022.syn3;

/**
 * Created by zhiguang on 2017/10/22.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        MyContainer mc = new MyContainer();
        for (int i = 0; i < 5; i++) {
            Thread t1 = new ThreadA(mc);
            Thread t2 = new ThreadA(mc);
            t1.start();
            t2.start();
        }

        //�����2, �������, �����������?
        //���ⲻ������, ����service����
        Thread.sleep(2);
        for (int i = 0; i < 5; i++) {
            System.out.println(mc.size());
        }
    }
}

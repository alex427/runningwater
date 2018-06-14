package alex.day1022.syn4;

/**
 * Created by zhiguang on 2017/10/23.
 */
public class MyService {
    //ͨ����������������, �����ܹ����̵߳����߿�����
    public  void doservice(MyLock lock) throws InterruptedException {
        synchronized(lock) {
            long start = System.currentTimeMillis();
            Thread.sleep(300);
            System.out.println(System.currentTimeMillis() - start);
        }
    }
}

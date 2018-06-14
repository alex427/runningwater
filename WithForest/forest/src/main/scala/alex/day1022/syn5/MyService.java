package alex.day1022.syn5;


/**
 * Created by zhiguang on 2017/10/23.
 */
public class MyService {
    public  void doserviceA(MyLock lock) throws InterruptedException {
        synchronized(lock) {
            long start = System.currentTimeMillis();
            Thread.sleep(300);
            System.out.println(Thread.currentThread().getName() + "  start " + start);
            System.out.println(Thread.currentThread().getName()+" "+(System.currentTimeMillis() - start));
        }
    }
    public  void doserviceB(MyLock lock) throws InterruptedException {
        synchronized(lock) {
            long start = System.currentTimeMillis();
            Thread.sleep(300);
            System.out.println(Thread.currentThread().getName() + "  start " + start);
            System.out.println(Thread.currentThread().getName()+" "+(System.currentTimeMillis() - start));
        }
    }
    public  void doserviceC(MyLock lock) throws InterruptedException {
        synchronized(lock) {
            long start = System.currentTimeMillis();
            Thread.sleep(300);
            System.out.println(Thread.currentThread().getName() + "  start " + start);
            System.out.println(Thread.currentThread().getName()+" "+(System.currentTimeMillis() - start));
        }
    }
}

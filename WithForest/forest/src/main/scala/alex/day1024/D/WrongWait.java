package alex.day1024.D;

/**
 * Created by zhiguang on 2017/10/24.
 */
public class WrongWait {
    //wait总是和锁关联着, 所以wait存在于同步语句中, 如果没有同步, 将抛出监视器非法状态异常
/*    public static void main(String[] args) throws InterruptedException {
        String a = "a";
        a.wait();
    }*/

    public static void main(String[] args) throws InterruptedException {
        String a = "a";
        synchronized (a){
            //需要明白, wait是锁调用的, 同步采用的锁和调用wait的锁是一把锁
            //那么,锁是怎么产生的呢?
            //上面同步块里面声明了那个对象, 那他就是锁. 这里同步时用了a当锁, 所以里面的调用的wait就应该是a
            //否则也会抛出监视器非法状态异常
            a.wait();
            System.out.println("a");
            a.notify();//程序走到wait就暂停了, 线程被放入了等待队列中, 所以尽管下面做了notify, 也是无用, 根本执行不到那里去.
        }
    }
}

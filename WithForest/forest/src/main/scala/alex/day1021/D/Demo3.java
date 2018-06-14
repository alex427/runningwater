package alex.day1021.D;

/**
 * Created by zhiguang on 2017/10/21.
 * suspend方法, 独占锁, 它挂起了, 但是不会释放锁, 别的线程也不能运行
 * 下面的代码
 * mt启动后, 立刻挂起, 然后mt2创建了, 启动了, 但是mt没有释放锁, 所以mt2不能运行, 控制台首先输出"走到这里了"
 * 接着mt resume()了, 执行, 执行完成, 释放锁, mt2又执行
 */
public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        Person p = new Person("jack",22);
        MyThread3 mt = new MyThread3(p);
        mt.setName("t1");
        mt.start();
        //mt.sleep(2000);
        mt.suspend();

        MyThread3 mt2 = new MyThread3(p);
        mt2.setName("t2");
        System.out.println("走到这里了");
        mt2.start();

        mt.resume();
    }
}

package alex.learn.designpattern.singlepattern;

import org.apache.commons.collections.list.SynchronizedList;

import java.util.ArrayList;
import java.util.List;

/**
 * zhiguang
 */
public class SingleMainDemo {

    public static List<SingleRuntime> list = new ArrayList();
    public static List<ParalleSingleRuntime> list2 = new ArrayList();
    public static List<StaticHolderRuntime> list3 = new ArrayList();


    public static void main(String [] args) throws InterruptedException {

        for(int i=0;i<20;i++){

            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    SingleRuntime sr =SingleRuntime.getInstance();
                    synchronized (SingleMainDemo.class){
                        list.add(sr);
                        System.out.println(list.size());
                    }
                }
            });
            th.start();
        }

        Thread.sleep(2000); //这里必须加上sleep, 否则程序迅速执行, 下面的list里面还是空的
        System.out.println(list.size());
        for(SingleRuntime sr : list){
            System.out.println(sr.hashCode());
        }



        System.out.println("-----------------------");

        for(int i=0;i<100;i++){
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    ParalleSingleRuntime sr =ParalleSingleRuntime.getInstance_b();
                    synchronized (SingleMainDemo.class){
                        list2.add(sr);
                    }
                }
            });
            th.start();
        }

        Thread.sleep(8000);
        System.out.println(list2.size());
        for(ParalleSingleRuntime psr : list2){
            System.out.println(psr.hashCode());
        }



        System.out.println("-----------------------");

        for(int i=0;i<100;i++){
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    StaticHolderRuntime sr  =  StaticHolderRuntime.getInstance();
                    StaticHolderRuntime sr2 = StaticHolderRuntime.getInstance();
                    synchronized (SingleMainDemo.class){
                        list3.add(sr);
                        list3.add(sr2);
                    }
                }
            });
            th.start();
        }

        Thread.sleep(8000);
        System.out.println(list3.size());
        for(StaticHolderRuntime shr : list3){
            System.out.println(shr.hashCode());
        }

    }
}

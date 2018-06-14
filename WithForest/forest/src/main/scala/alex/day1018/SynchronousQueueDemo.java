package alex.day1018;

import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by zhiguang on 2017/10/19.
 */
public class SynchronousQueueDemo {
    public static void main(String[] args){
        final SynchronousQueue<String> sq = new SynchronousQueue<String>();

        new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println(sq.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable(){
            public void run() {
                sq.add("aaa");
            }
        }).start();

        System.out.println(sq);
    }
}

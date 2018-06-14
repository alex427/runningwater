package alex.thread_features.myqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhiguang on 2017/10/17.
 */
public class ConcurrentLinkedQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedQueue<String> cq = new ConcurrentLinkedQueue();
        cq.add("a");
        cq.add("b");
        cq.offer("c");
        cq.offer("d");

        //System.out.println(cq.poll());
        //System.out.println(cq.size());
        //System.out.println(cq.peek());
        //System.out.println(cq.size());

        ArrayBlockingQueue<String> aq = new ArrayBlockingQueue<String>(5);
        aq.offer("aa", 1L, TimeUnit.SECONDS);
        aq.add("bb");
        aq.add("cc");
        System.out.println(aq.size());
        System.out.println(aq.add("dd"));
        System.out.println(aq.size());
        System.out.println(aq.offer("ee", 1L, TimeUnit.SECONDS));
        Thread.sleep(3);
        System.out.println(aq.offer("ff", 5L, TimeUnit.SECONDS));
        System.out.println(aq.add("gg"));

    }
}

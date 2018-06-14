package alex.thread_features.master_worker;

import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by zhiguang on 2017/10/19.
 */
public class Worker implements Runnable {

    private ConcurrentLinkedQueue<Task> taskqueue;
    private HashMap<String, Integer> resultset;

    public Worker(ConcurrentLinkedQueue<Task> taskqueue, HashMap<String, Integer> resultset) {
        this.taskqueue=taskqueue;
        this.resultset=resultset;
    }

    public void run() {
        while(true){
            Task task = this.taskqueue.poll();
            if(task != null){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int r = task.getAmount()*1000;
                System.out.println(Thread.currentThread().getName()+" is working");
                this.resultset.put(task.getName(),task.getAmount()*1000);
            } else {
                break;
            }
        }

    }
}

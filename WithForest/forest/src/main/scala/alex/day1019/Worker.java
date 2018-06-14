package alex.day1019;

import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by zhiguang on 2017/10/19.
 */
public class Worker implements Runnable {
    //����master��task���е�����, ͨ�����췽������
    private ConcurrentLinkedQueue<Task> taskqueue;
    private HashMap<String, Integer> resultset;

    public Worker(ConcurrentLinkedQueue<Task> taskqueue, HashMap<String, Integer> resultset) {
        this.taskqueue=taskqueue;
        this.resultset=resultset;
    }

    public void run() {
        while(true){
            Task task = this.taskqueue.poll();
            //ִ������
            //��������,ִ��
            if(task != null){
                //ģ��һ��
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int r = task.getAmount()*1000;
                System.out.println(Thread.currentThread().getName()+" is working");
                this.resultset.put(task.getName(),task.getAmount()*1000);
            } else {
                //û��������, �Զ��ҵ�
                break;
            }
        }

    }
}

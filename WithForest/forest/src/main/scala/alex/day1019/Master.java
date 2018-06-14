package alex.day1019;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by zhiguang on 2017/10/19.
 */
public class Master {
    //��Ҫһ��ʢ�����������   A
    //�����ɿͻ����ύ, ��ӽ���
    private ConcurrentLinkedQueue<Task> taskqueue = new ConcurrentLinkedQueue();
    //��Ҫһ��ʢ��worker������ B
    private HashMap<String, Thread> workermap = new HashMap<String, Thread>();
    //��Ҫһ��ʢ�Ž���������� C
    private HashMap<String, Integer> resultset = new HashMap<String, Integer>();

    public Master() {
        //��ʼ��ʱ, ��10��worker��ȥ, ����ָ��
        int p = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < p; i++) {
            Worker worker = new Worker(taskqueue, resultset);
            this.workermap.put("worker" + Integer.toString(i), new Thread(worker));
        }
    }

    //�����ύ
    public void submit(Task task) {
        this.taskqueue.add(task);
    }

    //����ִ��
    public void execute() {
        for (Map.Entry<String, Thread> en : workermap.entrySet()) {
            en.getValue().start();
        }
    }

    //����ִ�����, �˳�, true��ʾ���
    public boolean isComplete() {
        for (Map.Entry<String, Thread> en : workermap.entrySet()) {
            if (en.getValue().getState() != Thread.State.TERMINATED) {
                //���
                return false;
            }
        }
        return true;
    }

    //���ؽ����
    public HashMap<String, Integer> getResult() {
        return this.resultset;
    }
}

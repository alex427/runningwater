package alex.thread_features.master_worker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by zhiguang on 2017/10/19.
 */
public class Master {

    private ConcurrentLinkedQueue<Task> taskqueue = new ConcurrentLinkedQueue();
    private HashMap<String, Thread> workermap = new HashMap<String, Thread>();
    private HashMap<String, Integer> resultset = new HashMap<String, Integer>();

    public Master() {
        int p = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < p; i++) {
            Worker worker = new Worker(taskqueue, resultset);
            this.workermap.put("worker" + Integer.toString(i), new Thread(worker));
        }
    }

    public void submit(Task task) {
        this.taskqueue.add(task);
    }

    public void execute() {
        for (Map.Entry<String, Thread> en : workermap.entrySet()) {
            en.getValue().start();
        }
    }

    public boolean isComplete() {
        for (Map.Entry<String, Thread> en : workermap.entrySet()) {
            if (en.getValue().getState() != Thread.State.TERMINATED) {
                return false;
            }
        }
        return true;
    }

    public HashMap<String, Integer> getResult() {
        return this.resultset;
    }
}

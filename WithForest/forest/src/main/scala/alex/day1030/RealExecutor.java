package alex.day1030;

/**
 * Created by zhiguang on 2017/10/30.
 */
public class RealExecutor implements Executor{
    public void execute(MyRunnable runnable) {
        runnable.run();
    }
}

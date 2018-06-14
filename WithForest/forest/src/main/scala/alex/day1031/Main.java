package alex.day1031;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhiguang on 2017/10/31.
 */
public class Main {
    public static void main(String[] args){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        ExecutorService executor2 = Executors.newFixedThreadPool(5);
    }
}

package alex.thread_features.containers;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhiguang on 2018/4/30.
 */
public class HashmapProblem {
    //Hashmap线程不安全, 并发操作Hashmap, 最后不一定能得到正确的结果
    //这个demo,每次拿到的map.size都是不一样的
    private static HashMap<String,String> map = new HashMap();
    //但是ConcurrentHashMap可以做到这一点
    private static ConcurrentHashMap<String,String> cmap = new ConcurrentHashMap();

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 500; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++){
                        String key = Thread.currentThread().getName()+"bdkecd"+i;
                        map.put(key,"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                    }
                }
            }).start();

        }
        
        Thread.sleep(8000);
        System.out.println(map.size());


        for (int i = 0; i < 500; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++){
                        String key = Thread.currentThread().getName()+"bdkecd"+i;
                        cmap.put(key,"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                    }
                }
            }).start();

        }

        Thread.sleep(8000);
        System.out.println(cmap.size());


    }
}

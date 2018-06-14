package alex.thread_features.syncronizedd;

/**
 * Created by zhiguang on 2018/4/29.
 */
public class ThreadDemo10 {
    public static int count =0;
    public static void main(String[] args){
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                        while (true){
                            synchronized (ThreadDemo9.class){
                            if(count !=10){
                                count++;
                                System.out.println(Thread.currentThread().getName()+"  "+count);
                            } else {
                                //如果不捕获异常, 程序到这里就终止了, 5个线程都会在这里报错,停止.
                                try {
                                    int a = 8/0;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    //count++;
                                   // continue;   // 这里也可以在修改count值之后, 加countinue, 跳过错误.
                                }
                            }
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println(Thread.currentThread().getName()+"  "+"going");
                        }
                    }
                }
            },("thread-"+i)).start();
        }
    }
}

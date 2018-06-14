package alex.thread_features.base;

/**
 * Created by zhiguang on 2018/4/28.
 */
public class ThreadDemo1 {

    //共享数据1: 当前类的公共数据
    public static int a = 0;
    
    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<50;i++){
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (ThreadDemo1.class){
                        a=a+1;
                    }
                }
            });
            th.start();
        }

        Thread.sleep(2000);  //这里必须sleep, 否则还不等线程执行完成, 这里就输出了, 造成错误.
        System.out.println(a);
    }
}




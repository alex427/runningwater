package alex.thread_features.base;

/**
 * Created by zhiguang on 2018/4/28.
 */
public class ThreadDemo2 {

    public static void main(String[] args) throws InterruptedException {
        final int tt =0;  // 没有意义, 只是检验一下局部内部类对局部变量的访问限制, 必须加final
        final MyService ms = new MyService();
        for(int i=0;i<50;i++){
            Thread th = new Thread(ms);
            th.start();
        }
        
    }
}

//业务类
class MyService implements Runnable{
    private int a = 100;
    @Override
    public void run() {
        while(a >0){
            synchronized (MyService.class){
                if(a <= 0) {
                    break;
                }
                System.out.println(Thread.currentThread().getName() +"     "+ (a--));
            }
        }

    }
}
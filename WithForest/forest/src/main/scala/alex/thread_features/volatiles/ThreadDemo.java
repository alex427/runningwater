package alex.thread_features.volatiles;

/**
 * Created by zhiguang on 2018/4/29.
 */
public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        MtThread mt = new MtThread();
        Thread th1 = new Thread(mt);

        MtThread2 mt2 = new MtThread2();

        th1.start();
        mt2.start();
        Thread.sleep(5000);
        mt.setFlag(1);
        mt2.setFlag(1);
    }
}

class MtThread implements Runnable{
    private int flag=0;

    public void setFlag(int flag){
        this.flag=flag;
    }

    @Override
    public void run() {
        while (true){
            if(flag !=0){
               System.out.println("stop");
                break;
            } else {
                System.out.println("it is running ...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


class MtThread2 extends Thread{
    private int flag=0;

    public void setFlag(int flag){
        this.flag=flag;
    }

    @Override
    public void run() {
        while (true){
            if(flag !=0){
                System.out.println("stop");
                break;
            } else {
                System.out.println("it is running ...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
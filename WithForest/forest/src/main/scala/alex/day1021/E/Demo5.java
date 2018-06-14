package alex.day1021.E;

/**
 * Created by zhiguang on 2017/10/21.
 */
public class Demo5 {
    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(new Runnable() {
            public void run() {
                int count = 0;
                while(true){
                    System.out.println(count++);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t.setDaemon(true);
        t.start();
        Thread.sleep(3000); //3��֮��, ���߳�ִ�����, �ػ��߳�Ҳ���˳���
        Thread.yield();
    }
}

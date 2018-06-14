package alex.day1021.C;

/**
 * Created by zhiguang on 2017/10/21.
 */
public class MyThread2 extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 1500; i++) {
                if (this.interrupted()) {
                    System.out.println("线程标记终止了, 我要停下来了" + i);
                    throw new InterruptedException();
                }
                System.out.println(i);
            }
        } catch (Exception e) {
            System.out.println("进入catch语句");
            e.printStackTrace();
        }
        System.out.println("最后的输出...");
    }
}


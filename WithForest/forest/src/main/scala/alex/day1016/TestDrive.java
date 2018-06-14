package alex.day1016;

/**
 * Created by zhiguang on 2017/10/16.
 */
public class TestDrive {

    public static void main(String[] args){
        final ConThreadLocal ct = new ConThreadLocal();
        new Thread(new Runnable() {
            public void run() {
                ct.setTh("�߳�1");
                ct.getTh();
                ct.setMap("a", 1);
                ct.getMap();
            }
        },"t1").start();

        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ct.getTh(); //t2 null
                ct.getMap(); //{a=1}
            }
        },"t2").start();
    }
}

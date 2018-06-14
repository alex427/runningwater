package alex.day1023.LockChange;

/**
 * Created by zhiguang on 2017/10/24.
 */
public class Main {
    public static void main(String[] args){
        final Service service = new Service();

        new Thread(new Runnable() {
            public void run() {
                service.work();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                service.work();
            }
        }).start();
    }

}

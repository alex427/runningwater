package alex.day1016;

/**
 * Created by zhiguang on 2017/10/16.
 */
public class Demo1Drive {
    public static void main(String[] args){
        new Thread(new Runnable() {
            public void run() {
                System.out.println(InnerSingleton.getInstace());;
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                System.out.println(InnerSingleton.getInstace());;
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                System.out.println(InnerSingleton.getInstace());;
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                System.out.println(InnerSingleton.getInstace());;
            }
        }).start();
    }
}

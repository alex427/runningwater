package alex.day1024.B;

/**
 * Created by zhiguang on 2017/10/24.
 */
public class Main {
    public static void main(String[] args){
        MyThread[] arr = new MyThread[100];

        for (int i=0;i<100;i++){
            arr[i] = new MyThread();
        }

        for (int i=0;i<100;i++){
            arr[i].start();
        }
    }
}

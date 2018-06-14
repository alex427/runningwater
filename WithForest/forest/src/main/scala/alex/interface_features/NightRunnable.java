package alex.interface_features;

/**
 * Created by zhiguang on 2018/4/27.
 */
public interface NightRunnable {
    final int all = 55;
    int a = 100;
    static int b =101;

    void nightrun();

    //接口不接受静态方法
    /*static void runfast(){

    }*/
}

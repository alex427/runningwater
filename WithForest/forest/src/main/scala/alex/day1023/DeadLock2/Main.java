package alex.day1023.DeadLock2;

/**
 * Created by zhiguang on 2017/10/23.
 */
public class Main {

    public static void main(String[] args){
        String a = "123";
        System.out.println(a.hashCode());
        a="456";
        System.out.println(a.hashCode());
    }
}

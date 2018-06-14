package alex.day1024.D;

/**
 * Created by zhiguang on 2017/10/24.
 */
public class WrongNotify {
    //notify��wait�ĵ�����һ����, ��������������, ����Ĵ��������û����, ��Ȼ����
/*
    public static void main(String[] args){
        String a ="a";
        a.notify();
    }
*/

    public static void main(String[] args){
        String a ="a";
        synchronized (a){
            a.notify();
        }

    }
}

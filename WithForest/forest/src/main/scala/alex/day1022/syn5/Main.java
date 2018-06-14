package alex.day1022.syn5;

/**
 * Created by zhiguang on 2017/10/23.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();
        MyLock lock = new MyLock();

        //��Ȼ�����̵߳��õ��ǲ�ͬ�ķ���, ������Ϊ����ͬһ����, ���ͬ��ִ��
        Thread th = new ThreadF(service,lock);
        Thread th2 = new ThreadG(service,lock);
        Thread th3 = new ThreadH(service,lock);

        th.start();
        th2.start();
        th3.start();
        lock.doserviceD(lock); //�����������ͬ������, ֻҪ����ͬһ����, ����ͬ��
    }
}

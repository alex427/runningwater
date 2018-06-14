package alex.day1017;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhiguang on 2017/10/17.
 * ��Ч���������޽����
 */
public class QueueDemo {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedQueue<String> cq = new ConcurrentLinkedQueue();
        cq.add("a");
        cq.add("b");
        cq.offer("c");
        cq.offer("d");

        //System.out.println(cq.poll()); //��ȡ���Ƴ��˶��е�ͷ������˶���Ϊ�գ��򷵻� null��
        //System.out.println(cq.size());
        //System.out.println(cq.peek()); //����, ��ȡ�����Ƴ��˶��е�ͷ������˶���Ϊ�գ��򷵻� null��
        //System.out.println(cq.size());

        //�н���������
        ArrayBlockingQueue<String> aq = new ArrayBlockingQueue<String>(5);
        aq.offer("aa", 1L, TimeUnit.SECONDS);//��ָ����Ԫ�ز���˶��е�β��������ö������������ڵ���ָ���ĵȴ�ʱ��֮ǰ�ȴ����õĿռ䡣
        aq.add("bb"); // ��ָ����Ԫ�ز��뵽�˶��е�β����������������Ҳ��ᳬ���ö��е����������ڳɹ�ʱ���� true������˶������������׳� IllegalStateException��
        aq.add("cc");
        System.out.println(aq.size());
        System.out.println(aq.add("dd"));
        System.out.println(aq.size());
        System.out.println(aq.offer("ee", 1L, TimeUnit.SECONDS));
        Thread.sleep(3);
        System.out.println(aq.offer("ff", 5L, TimeUnit.SECONDS)); //�������, 5���, ����false
        System.out.println(aq.add("gg")); //�������, �����׳��쳣

    }
}

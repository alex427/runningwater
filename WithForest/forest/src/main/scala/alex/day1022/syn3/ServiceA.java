package alex.day1022.syn3;

/**
 * Created by zhiguang on 2017/10/22.
 * ҵ����
 */
public class ServiceA {

    public void addData(MyContainer<String> mc, String data){
        //����Ӧ�ü�ͬ��
        //�߳�1����, �ж�С��1, ��Ҫ����, �߳�2����CPU, �߳�2�ж�Ҳ��С��1, ׼������, �߳�1�ֶ��CPU, ִ��add,Ȼ���ͷ�CPU,
        //�߳�2�û�CPU, ִ��add, ��ʱ�������������2��Ԫ����, ��ȻҲ���ܳ���3�����߸����Ԫ��, ����ǲ���Ԥ֪��.
        //���Ǽ����ü�mc, ������ͬһ����
        if(mc.size() < 1){
            mc.add(data);
        } else {
            System.out.println("already full...");
        }
    }

}

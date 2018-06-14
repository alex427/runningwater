package alex.thread_features.myqueue;

import java.util.concurrent.DelayQueue;

/**
 * Created by zhiguang on 2017/10/18.
 */
public class Netba extends Thread{
    //持有队列
    private  DelayQueue<Wangmin> dq = new DelayQueue<Wangmin>();
    private boolean guanmen = false;

    //上机: 向队列中加入元素
    public  void shangji(int id, String name, long balance){
        Wangmin wm = new Wangmin(id,name, balance*1000+System.currentTimeMillis());
        dq.add(wm);
        System.out.println(name+"开始上机"+balance+"小时");
    }

    //下机:从队列中取出元素
    public void xaiji(Wangmin w){
        System.out.println(w.getName()+"时间到, 下机");
    }

    @Override
    public void run(){
        while(!guanmen){
            try {
                Wangmin wm = dq.take();
                xaiji(wm);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        Netba netba = new Netba();
        Thread th = new Thread(netba);
        th.start();

        //网民来了, 加入队列
        netba.shangji(123, "jack", 1);
        netba.shangji(345, "mack", 5);
        netba.shangji(555, "zack", 3);
    }
}

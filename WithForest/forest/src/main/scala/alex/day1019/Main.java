package alex.day1019;

import java.util.Random;

/**
 * Created by zhiguang on 2017/10/20.
 */
public class Main {
    //模拟客户端
    public static void main(String[] args){
        Master master = new Master();
        //提交任务
        Random r = new Random(10000);
        for(int i=0;i<100;i++){
            Task t =  new Task("任务"+Integer.toString(i),r.nextInt());
            master.submit(t);
        }
        //执行任务
        master.execute();
        //获取结果
        while(true){
            if(master.isComplete()){
                System.out.println(master.getResult());
                System.out.println("任务完成...");
                break;
            }
        }

    }
}

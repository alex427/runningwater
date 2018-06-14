package alex.day1021.C;

/**
 * Created by zhiguang on 2017/10/21.
 */
public class MyThread extends Thread{
    @Override
    public void run(){
        for(int i=0;i<500000;i++){
            if(this.interrupted()){
                System.out.println("线程标记终止了, 我要停下来了" +i);
                break;
            }
            System.out.println(i);
        }
            System.out.println("最后的输出...");
    }
}

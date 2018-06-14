package alex.learn.threada;

/**
 * Created by zhiguangai on 2017/9/11.
 */
public class MyThreadA extends Thread{

    public void run(){
        int i= 5000;
       while(i>0){
           System.out.println(" I am the A thread");
           i--;
       }
    }
}


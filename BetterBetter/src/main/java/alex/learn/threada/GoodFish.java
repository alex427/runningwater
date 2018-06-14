package alex.learn.threada;

/**
 * Created by zhiguangai on 2017/9/11.
 */
public class GoodFish {
    //多线程: 主线程在栈中另外开辟的一个相对独立的空间, 它和主线程交替(平行)执行.
    public static void main(String [] args){
        Thread th = new MyThreadA();
        th.start();
        int i= 5000;
        while(i>0){
            System.out.println(" I am the main thread");
            i--;
        }
    }
}

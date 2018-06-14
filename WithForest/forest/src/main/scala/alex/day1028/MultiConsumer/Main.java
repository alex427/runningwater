package alex.day1028.MultiConsumer;

/**
 * Created by zhiguang on 2017/10/28.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyStack2<String> stack = new MyStack2();
        Thread t1 = new ThreadA(stack);
        Thread t2 = new ThreadB(stack);

        for (int i=0;i<5;i++){
            new Thread(t1).start();
        }
        for (int i=0;i<5;i++){
            new Thread(t2).start();
        }
    }

}

//生产者
class ThreadA extends Thread {
    private MyStack2 stack;
    public ThreadA(MyStack2 stack){
        this.stack = stack;
    }

    @Override
    public void run(){
        this.stack.push("AA");
    }
}

//消费者
class ThreadB extends Thread {
    private MyStack2 stack;
    public ThreadB(MyStack2 stack){
        this.stack = stack;
    }

    @Override
    public void run(){
        System.out.println(this.stack.pop());
    }
}








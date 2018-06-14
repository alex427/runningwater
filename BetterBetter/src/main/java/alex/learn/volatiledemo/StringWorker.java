package alex.learn.volatiledemo;

/**
 * Created by zhiguangai on 2017/10/24.
 */
public class StringWorker {
    volatile private boolean flag = false;

    public void setFlag(boolean flag){
        this.flag=flag;
    }

    public void work(){
        while(!flag){
            System.out.println("I am dieing here....");
        }
        System.out.println("get out");
    }
}

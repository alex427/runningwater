package alex.day1024.C;

/**
 * Created by zhiguangai on 2017/10/24.
 */
public class StringWorker {
    private boolean flag = false;
    private String str = "aa";

    public void setFlag(boolean flag){
        this.flag=flag;
    }

    public void work(){
        while(!flag){
            synchronized(str){
                System.out.println("I am dieing here....");
            }
        }
        System.out.println("get out");
    }
}

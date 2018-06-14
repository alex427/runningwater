package alex.day1023.LockChange;

/**
 * Created by zhiguang on 2017/10/24.
 */
public class Service {
    private String lock ="123";
    
    public void work(){
        synchronized (lock){
            System.out.println("get lock...");
            //�޸���
            lock="456";
            System.out.println(lock);
        }
    }
}

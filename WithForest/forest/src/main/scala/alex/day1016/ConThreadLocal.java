package alex.day1016;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhiguang on 2017/10/16.
 */
public class ConThreadLocal {
    //持有ThreadLocal
    public static ThreadLocal<String> th = new ThreadLocal<String>();
    
    public static Map<String,Integer> map = new HashMap<String, Integer>();

    public void setTh(String value){
        this.th.set(value);
    }
    
    public void getTh(){
        System.out.println(Thread.currentThread().getName() + " " + this.th.get());
    }
    
    public void setMap(String key, Integer value){
        this.map.put(key, value);
    }
    
    public void getMap(){
        System.out.println(this.map);
    }

}

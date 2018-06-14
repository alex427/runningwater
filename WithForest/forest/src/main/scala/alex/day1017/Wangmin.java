package alex.day1017;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhiguang on 2017/10/18.
 */
public class Wangmin implements Delayed{
    private int id;
    private String name;
    private long balance;
    private TimeUnit unit = TimeUnit.SECONDS;

    //DelayQueue在执行take方法时,会不断循环调用这个方法, 如果这个返回值<=0, 就对元素执行poll操作
    public long getDelay(TimeUnit unit) {
        return this.balance - System.currentTimeMillis();
    }

    public int compareTo(Delayed o) {
        Wangmin w = (Wangmin)o;
        return this.getDelay(this.unit) > w.getDelay(this.unit)?1:-1;
    }

    public Wangmin(int id, String name, long balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String toString(){
        return this.name+", "+this.balance +"Ԫ";
    }
}

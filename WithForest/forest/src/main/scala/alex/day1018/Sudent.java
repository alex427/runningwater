package alex.day1018;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhiguang on 2017/10/18.
 */
public class Sudent implements Delayed{
    private int id;
    private String name;
    private int score;

    public Sudent(int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    private TimeUnit unit = TimeUnit.SECONDS;

    public long getDelay(TimeUnit unit) {
        return this.score--;
    }

    public int compareTo(Delayed o) {
        Sudent s = (Sudent)o;
        return this.score-s.score >0?1:-1;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

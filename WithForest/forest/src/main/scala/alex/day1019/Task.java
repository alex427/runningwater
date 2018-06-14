package alex.day1019;

/**
 * Created by zhiguang on 2017/10/19.
 */
public class Task {

    private String name;
    private int amount;

    public Task(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

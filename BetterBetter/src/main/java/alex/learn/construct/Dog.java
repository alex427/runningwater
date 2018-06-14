package alex.learn.construct;

/**
 * Created by zhiguangai on 2017/9/8.
 */
public class Dog {

    private String color;
    private int leg;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLeg() {
        return leg;
    }

    public void setLeg(int leg) {
        this.leg = leg;
    }

    @Override
    public String toString() {
        return "Dog{" + "color='" + color + '\'' + ", leg=" + leg + '}';
    }
}

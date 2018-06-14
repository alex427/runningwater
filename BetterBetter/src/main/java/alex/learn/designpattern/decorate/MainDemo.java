package alex.learn.designpattern.decorate;

/**
 * zhiguang
 */
public class MainDemo {
    public static void main(String [] args){
        Beverage coffe = new Coffe();
        coffe = new Milk(coffe);
        coffe = new Sugar(coffe);
        int price = coffe.getprice();
        System.out.println(price);
    }
}

package alex.learn.designpattern.decorate;

/**
 * zhiguang
 */
public class Milk extends Beverage{

    private Beverage beverage;

    public Milk(Beverage beverage){
        this.beverage=beverage;
    }

    @Override
    public int getprice() {
        return this.beverage.getprice()+5;
    }

    public Beverage getBeverage() {
        return beverage;
    }

    public void setBeverage(Beverage beverage) {
        this.beverage = beverage;
    }

}

package zhiguang.designpattern.factory.abstractfactory.store;

public class Customer {

	public static void main(String[] args) {
		PizzaStore pizzaStore = new BeijingPizzaStore();
		pizzaStore.orderPizza("salt");
	}

}

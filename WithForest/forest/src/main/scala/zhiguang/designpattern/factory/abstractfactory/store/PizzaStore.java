package zhiguang.designpattern.factory.abstractfactory.store;


import zhiguang.designpattern.factory.abstractfactory.pizza.Pizza;

public abstract class PizzaStore {
	
	Pizza orderPizza(String pizzaname){
		Pizza pizza = createPizza(pizzaname);
		System.out.println(pizza);
		pizza.bake();
		pizza.cut();
		pizza.pack();
		return pizza;
	}

	protected abstract Pizza createPizza(String pizzaname);
}

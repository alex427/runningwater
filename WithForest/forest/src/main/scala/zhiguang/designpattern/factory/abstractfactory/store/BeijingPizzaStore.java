package zhiguang.designpattern.factory.abstractfactory.store;


import zhiguang.designpattern.factory.abstractfactory.ingredient.BeijingIgFactory;
import zhiguang.designpattern.factory.abstractfactory.pizza.HotPizza;
import zhiguang.designpattern.factory.abstractfactory.pizza.Pizza;
import zhiguang.designpattern.factory.abstractfactory.pizza.SaltPizza;
import zhiguang.designpattern.factory.abstractfactory.pizza.SweetPizza;

public class BeijingPizzaStore extends PizzaStore{
	
	//写错了
	@Override
	public Pizza createPizza(String pizzaname) {
		if("sweet".equals(pizzaname)){
			return new SweetPizza(new BeijingIgFactory());
		} else if("hot".equals(pizzaname)){
			return new HotPizza(new BeijingIgFactory());
		} else if("salt".equals(pizzaname)){
			return new SaltPizza(new BeijingIgFactory());
		} else{
			return null;
		}		
	}

}

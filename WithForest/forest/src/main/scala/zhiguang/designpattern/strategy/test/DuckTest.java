/**
 * 
 */
package zhiguang.designpattern.strategy.test;


import zhiguang.designpattern.strategy.behaviors.FlyHighBehavior;
import zhiguang.designpattern.strategy.behaviors.FlyLowBehavior;
import zhiguang.designpattern.strategy.behaviors.SpeakGoodBehavior;
import zhiguang.designpattern.strategy.child.GreenDuck;
import zhiguang.designpattern.strategy.child.RedDuck;

/**
 * @author zhiguang
 *
 */
public class DuckTest {
	
	public static void main(String[] args) {
		 GreenDuck greenDuck = new GreenDuck();
		 RedDuck redDuck = new RedDuck();
		 greenDuck.setFly(new FlyHighBehavior());
		 greenDuck.fly();
		 greenDuck.setSpeak(new SpeakGoodBehavior());
		 greenDuck.speak();
		 greenDuck.dosome();
		 
		 
		 redDuck.setFly(new FlyLowBehavior());
		 redDuck.fly();
		 redDuck.dosome();
		 
	}

}

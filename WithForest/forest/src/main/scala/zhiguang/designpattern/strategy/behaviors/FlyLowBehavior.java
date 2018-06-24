/**
 * 
 */
package zhiguang.designpattern.strategy.behaviors;


import zhiguang.designpattern.strategy.interfaces.Fly;

/**
 * @author zhiguang
 *
 */
public class FlyLowBehavior implements Fly {

	@Override
	public void fly() {
		System.out.println("I can fly low");
		
	}

}

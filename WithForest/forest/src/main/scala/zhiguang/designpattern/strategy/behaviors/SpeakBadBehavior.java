/**
 * 
 */
package zhiguang.designpattern.strategy.behaviors;


import zhiguang.designpattern.strategy.interfaces.Speak;

/**
 * @author zhiguang
 *
 */
public class SpeakBadBehavior implements Speak {

	@Override
	public void speak() {
		System.out.println("i can speak bad");
		
	}

}

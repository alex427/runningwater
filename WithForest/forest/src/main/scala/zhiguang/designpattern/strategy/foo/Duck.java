/**
 * 
 */
package zhiguang.designpattern.strategy.foo;


import zhiguang.designpattern.strategy.interfaces.Fly;
import zhiguang.designpattern.strategy.interfaces.Speak;

/**
 * @author zhiguang
 *
 */
public abstract class Duck {
	public String color;
	Fly fly;
	Speak speak;
	
	public void dosome(){
		System.out.println("我来自抽象父类");
	}
	
	//关键
	public void fly(){
		fly.fly();
	}
	//关键
	public void speak(){
		speak.speak();
	}
	

	public Fly getFly() {
		return fly;
	}

	public void setFly(Fly fly) {
		this.fly = fly;
	}

	public Speak getSpeak() {
		return speak;
	}

	public void setSpeak(Speak speak) {
		this.speak = speak;
	}
	
	
	
}

package zhiguang.designpattern.command.concretecommand;


import zhiguang.designpattern.command.command.Command;
import zhiguang.designpattern.command.cook.Wang;

public class ChineseFood implements Command {
	
	private Wang wang;
	
	public ChineseFood(Wang wang){
		this.wang = wang;
	}

	@Override
	public void cook() {
		
		wang.chaofan();
	}

	@Override
	public boolean canCook() {
		
		return false;
	}

}

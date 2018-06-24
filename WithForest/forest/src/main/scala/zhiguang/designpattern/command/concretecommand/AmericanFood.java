package zhiguang.designpattern.command.concretecommand;


import zhiguang.designpattern.command.command.Command;

public class AmericanFood implements Command {

	@Override
	public void cook() {
		
		
	}

	@Override
	public boolean canCook() {
		
		return false;
	}

}

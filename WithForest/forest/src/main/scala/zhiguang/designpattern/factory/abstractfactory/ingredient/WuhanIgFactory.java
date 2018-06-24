package zhiguang.designpattern.factory.abstractfactory.ingredient;

public class WuhanIgFactory implements IngredientFactory{

	@Override
	public String createPepper() {
		
		return "wuhanPepper";
	}

	@Override
	public String createBarbe() {
		
		return "wuhanBarbe";
	}

	@Override
	public String createGarlic() {
		
		return "wuhanGarlic";
	}

	@Override
	public String createMint() {
		
		return "wuhanMint";
	}

}

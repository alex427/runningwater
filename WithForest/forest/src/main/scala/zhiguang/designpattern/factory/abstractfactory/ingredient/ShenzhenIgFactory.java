package zhiguang.designpattern.factory.abstractfactory.ingredient;

public class ShenzhenIgFactory implements IngredientFactory{

	@Override
	public String createPepper() {
		
		return "shenzhenPepper";
	}

	@Override
	public String createBarbe() {
		
		return "shenzhenBarbe";
	}

	@Override
	public String createGarlic() {
		
		return "shenzhenGarlic";
	}

	@Override
	public String createMint() {
		
		return "ShenzhenMint";
	}

}

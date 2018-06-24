package zhiguang.designpattern.factory.abstractfactory.ingredient;

public class BeijingIgFactory implements IngredientFactory{

	@Override
	public String createPepper() {
		
		return "BeijingPepper";
	}

	@Override
	public String createBarbe() {
		
		return "BeijingBarbe";
	}

	@Override
	public String createGarlic() {
		
		return "BeijingGarlic";
	}

	@Override
	public String createMint() {
		
		return "BeijingMint";
	}

}

package zhiguang.designpattern.factory.abstractfactory.pizza;

//
public abstract class Pizza {
	
	String pepper;
	String barbe;
	String garlic;
	String mint;
	
	//让子类去决定到底用哪种原料
	abstract void prepareIngredient();
	
	//在父类中定义好, 必须按照这个步骤生产.
	public void bake(){
		System.out.println("烘烤30分钟");
	}
	
	public void cut(){
		System.out.println("切成4份");
	}
	
	public void pack(){
		System.out.println("纸盒包装");
	}

	@Override
	public String toString() {
		return "Pizza [pepper=" + pepper + ", barbe=" + barbe + ", garlic=" + garlic + ", mint=" + mint + "]";
	}
		
}

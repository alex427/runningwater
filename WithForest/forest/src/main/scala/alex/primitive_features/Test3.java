package alex.primitive_features;
/**
 * 7、 编写程序，打印1到100之内的整数，但数字中包含7的要跳过，
 * 例如：17、27、71、72【已完成ok】
 * @author JX
 *
 */
public class Test3 {
	public static void main(String[] args) {
		//demo1();
		demo2();
	}

	public static void demo1() {
		for(int i = 1;i<=100;i++) {
			if(!(i+"").contains("7")) {
				System.out.println(i);
			}
		}
	}
	public static void demo2(){
		for(int i = 1;i<=100;i++) {
			int a = i%10;//a:个位
			int b = i/10%10;//b:十位
			if(!(a==7||b==7)) {
				System.out.println(i);
			} 
		}
	}
}

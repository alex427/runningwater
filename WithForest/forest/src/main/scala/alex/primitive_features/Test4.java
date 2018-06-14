package alex.primitive_features;
/**
 * 3、 编写程序计算12+22+32+....+1002的和.
 * @author JX
 *
 */
public class Test4 {
	public static void main(String[] args) {
		int i = 12;
		int sum = 0;
		while(i<=1002) {
			sum+=i;
			i+=10;
		}
		System.out.println(sum);
	}
}

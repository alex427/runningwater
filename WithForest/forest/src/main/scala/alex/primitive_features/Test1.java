package alex.primitive_features;
/**
 * 判断101-200之间有多少个素数，并输出所有素数。 
 * 程序分析：判断素数的方法：用一个数分别去除2到sqrt(这个数)，如果能被整除， 则表明此数不是素数，反之是素数。
 * @author JX
 * 7、 编程打印所有的3位质数，质数特点：只能被1和其本身整除
 * 和上面的题目一样的，只需要改一下for循环中i的起始和结束的值
 *
 */
public class Test1 {
	public static void main(String[] args) {
		//1.定义计数器
		int count = 0;
		for(int i = 101;i<200;i++) {
			int temp = (int) Math.sqrt(i);
			int j = 2;
			for(;j<=temp;j++) {
				if(i%j==0){
					break;
				}
			}
			if(j>temp) {
				count++;
				System.out.print(i+" ");
			}
		}
		System.out.println();
		System.out.println("101-200的素数个数为:"+count);
	}
}

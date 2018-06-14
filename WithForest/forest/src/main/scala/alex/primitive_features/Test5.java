package alex.primitive_features;
/**
 * 3、 定义一个二维int数组，编写代码获取最小元素。
 * @author JX
 *
 */
public class Test5 {
	public static void main(String[] args) {
		int[][] arr = {{10,20,30},{3,2,1},{4,5,6},{}};
		int min = arr[0][0];//假设arr[0][0]的值是最小的
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				if(min>arr[i][j]) {
					min = arr[i][j];
				}
			}
		}
		System.out.println(min);
	}
}

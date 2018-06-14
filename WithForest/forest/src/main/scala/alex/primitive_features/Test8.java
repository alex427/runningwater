package alex.primitive_features;

/**
 * 给定一个整形数组,将数组最大元素与第一位元素进行交换,最小元素与最后一位元素进行交换
 * 
 * @author JX
 * 
 */
public class Test8 {
	public static void main(String[] args) {
		int[] arr = { 89, 34, 56, 78, 23, 4, 54, 79, 87 };
		int max = getNum(arr, true); // 获取最大值索引
		int min = getNum(arr, false); // 获取最小值索引
		printArr(arr, max, min); // 交换并打印
	}

	public static void printArr(int[] arr, int max, int min) {
		int temp = arr[max]; // 交换最大元素
		arr[max] = arr[0];
		arr[0] = temp;

		int sum = arr[arr.length - 1]; // 交换最小元素
		arr[arr.length - 1] = arr[min];
		arr[min] = sum;

		for (int i : arr) { // 遍历数组输出
			System.out.print(i + " ");
		}
	}

	public static int getNum(int[] arr, boolean flag) {
		int num = 0; // 假设数组的第一个元素为最值
		for (int i = 0; i < arr.length; i++) { // 遍历数组
			if (flag) { // 最大值
				if (arr[num] < arr[i]) {
					num = i;
				}
			} else {
				if (arr[num] > arr[i]) { // 最小值
					num = i;
				}
			}
		}
		return num; // 返回最值索引
	}

}

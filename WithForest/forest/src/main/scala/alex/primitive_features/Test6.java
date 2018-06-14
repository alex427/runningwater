package alex.primitive_features;

import java.util.Arrays;

/**
 * 6、 数组去重复，例如: 原始数组是{4,2,4,6,1,2,4,7,8}，得到结果{4,2,6,1,7,8}
 * 
 * @author
 * 
 */
public class Test6 {
	public static void main(String[] args) {
		int[] arr = { 4, 2, 4, 6, 1, 2, 4, 7, 8 };
		arr = getSingleArr(arr);
		System.out.println(Arrays.toString(arr));
	}

	private static int[] getSingleArr(int[] arr) {
		int index = 0;
		int[] newArr = new int[arr.length];
		for (int i : arr) {
			boolean flag = false;
			for (int j : newArr) {
				if (i == j) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				newArr[index++] = i;
			}
		}
		//return Arrays.copyOf(newArr, index);
		int[] returnArr = new int[index];
		for(int i=0;i<index;i++) {
			returnArr[i] = newArr[i];
		}
		return returnArr;
	}
}

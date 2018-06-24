package zhiguang.datastructure.staticlinear;

import java.util.Arrays;

//用数组实现, 不是很好, 需要判断数组是否满了, 还得扩容
public class StacticLinear {

	private static Object[] arr = new Object[] { "a", "b", "c", "d" };

	public static void main(String[] args) {
		insert(arr, 4, "f");

	}

	// 在静态链表第i个元素之前, 插入新的元素e
	public static Boolean insert(Object[] arr, int i, Object ele) {
		// 判断i是否合法
		if (i < 0 || i >= arr.length) {
			System.out.println("插入失败");
			return true;
		}

		// 如果i等于链表长度, 则将链表扩容一倍, 复制旧链表,再插入新元素
		if (i == arr.length - 1) {
			Object[] arr2 = new Object[arr.length * 2];
			for (int a = 0; a < arr.length; a++) {
				arr2[a] = arr[a];
			}
			arr2[arr.length] = ele;
			arr = arr2;
			System.out.println("插入成功, 新链表: " + Arrays.toString(arr));
			return true;
		}

		// 如果i小于链表长度, 而且链表空间没用完, 则直接插入
		if (i < arr.length - 1) {
			//判断链表未满
			if (arr[arr.length - 1] == null) {
				Object[] arr2 = new Object[arr.length];
				for (int a = 0; a < i; a++) {
					arr2[a] = arr[a];
				}
				arr2[i] = ele;
				for (int b = i + 1; b < arr2.length; b++) {
					arr2[b] = arr[b - 1];
				}
				arr = arr2;
				System.out.println("插入成功, 新链表: " + Arrays.toString(arr));
				return true;
			} else {
				//链表满了, 扩容
				Object[] arr2 = new Object[arr.length * 2];
				for (int a = 0; a < i; a++) {
					arr2[a] = arr[a];
				}
				arr2[i] = ele;
				for (int b = i + 1; b < arr2.length; b++) {
					arr2[b] = arr[b - 1];
				}
				arr = arr2;
				System.out.println("插入成功, 新链表: " + Arrays.toString(arr));
				return true;
			}
		} 

		return false;
	}

}

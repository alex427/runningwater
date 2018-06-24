package zhiguang.datastructure.staticlinear;

import java.util.Arrays;

public class ListArray {
	// 长度
	private int len;
	// 持有数据集合
	private Object[] arr;

	// 分配
	public ListArray(int maxsize) {
		arr = new Object[maxsize];
		len = arr.length;
	}

	// 判断集合是满了
	public boolean isFull() {
		if (arr[len - 1] == null) {
			System.out.println("集合未满");
			return true;
		}
		System.out.println("集合已满");
		return false;
	}

	public boolean add(int i, Object ele) {
		// 判断i是否合法
		if (i < 0 || i > len) {
			System.out.println("插入失败");
			return true;
		}

		if (isFull()) {
			// 集合未满
			// 尾部插入
			arr[len - 1] = ele;
			// 非尾部插入
			Object[] arr2 = new Object[len];
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
			// 集合满了
			// 尾部插入: 将链表扩容一倍, 复制旧链表,再插入新元素
			if (i == len) {
				Object[] arr2 = new Object[len * 2];
				for (int a = 0; a < len; a++) {
					arr2[a] = arr[a];
				}
				arr2[len] = ele;
				arr = arr2;
				System.out.println("插入成功, 新链表: " + Arrays.toString(arr));
				return true;
			}
			// 非尾部插入
			if (i < len) {
				Object[] arr2 = new Object[len * 2];
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
	
	//移除指定位置的元素
	public Object remove(int pos){
		//判断指定位置的合法性
		if(pos<0 || pos>len-1 || arr[pos] == null){
			System.out.println("目标位置不存在");
			return null;
		}
		//保存该位置的值, 用于返回
		Object o = arr[pos];
		//移除目标值, 将它后面的元素全部向前移动一位
		for (int i = pos+1;i<len;i++){
			arr[i-1] = arr[i];
		}
		//数组长度减1
		len = len-1;
		System.out.println("当前链表: " + Arrays.toString(arr));
		return o;
	}
	
	
	//获取长度
	public int getLen(){
		return len;
	}
	
	//清空
	public void clear(){
		arr = new Object[0];
		len = arr.length;
	}

}

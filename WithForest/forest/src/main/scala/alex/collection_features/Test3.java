package alex.collection_features;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 12：编写程序，生成5个1至10之间的随机整数，存入一个List集合，编写方法对List集合进行排序
 * （自定义排序算法，禁用Collections.sort方法和TreeSet），然后遍历集合输出
 * 注意:这个题目如果改为不能重复的5个数,就不能用list的了,可以用HashSet
 * @author JX
 *
 */
public class Test3 {
	public static void main(String[] args) {
		List<Integer> list = getRandomNum();
		System.out.println("排序前:"+list);
		sort(list);
		System.out.println("排序后:"+list);
	}
	//对List集合中的数字进行从小到大的排序
	private static void sort(List<Integer> list) {
		//1.将list集合转成Integer类型的数组
		Integer[] arr = list.toArray(new Integer[list.size()]);
		//2.定义冒泡排序算法
		for(int i = 0;i<arr.length-1;i++) {
			for(int j = 0;j<arr.length-1-i;j++) {
				if(arr[j]>arr[j+1]) {//3.自动拆箱
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		//4.将排序后数组中的元素重新放入集合中
		for(int i = 0;i<arr.length;i++) {
			list.set(i, arr[i]);
		}
	}
	//生成5个1-10的随机数
	public static List<Integer> getRandomNum() {
		//1.创建ArrayList集合存放生成的随机数
		List<Integer> list = new ArrayList<Integer>();
		//2.创建Random对象,用于生成随机数
		Random r = new Random();
		//3.将生成的5个随机数存入list集合中
		while(list.size()<5) {
			int num = r.nextInt(10)+1;
			//如果要去重
			/*if(!list.contains(num)) {
				list.add(num);
			}*/
			list.add(num);
		}
		return list;
	}
}

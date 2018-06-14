package alex.collection_features;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Test6 {
	/*
	 * 1、有类似这样的字符串："1.2,3.4,5.6,7.8,5.56,44.55"请按照要求，依次完成以下试题。
	 * (1)以逗号作为分隔符，把已知的字符串分成一个String类型的数组，数组中的每一个元素类似于"1.2","3.4"这样的字符串
	 * (2)把数组中的每一个元素以
	 * "."作为分隔符，把"."左边的元素作为key，右边的元素作为value，封装到Map中，Map中的key和value都是Object类型。
	 * (3)把map中的key封装的Set中，并把Set中的元素输出。
	 * (4)把map中的value封装到Collection中，把Collection中的元素输出。
	 */
	public static void main(String[] args) {
		//1.定义字符串
		String str = "1.2,3.4,5.6,7.8,5.56,44.55";
		//2.以","切割成字符串数组
		String[] strArr = str.split(",");
		//3.定义Map集合,为了保证不去重,而且按照存入的顺序,定义比较器,直接返回1
		Map<Object,Object> map = new TreeMap<>(new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {				
				return 1;
			}
		});
		//4.遍历strArr数组,将元素用点切割,切割后索引0元素作为key,索引1元素作为value,放入map集合中
		for(String string : strArr) {
			String[] subArr = string.split("\\.");
			map.put(subArr[0], subArr[1]);
		}
		//5.把map中的key封装的Set中，并把Set中的元素输出。
		Set<Object> set = map.keySet();
		//6.获取迭代器
		Iterator<Object> it = set.iterator();
		//7.遍历输出
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println("------------------");
		//8.把map中的value封装到Collection中，把Collection中的元素输出。
		Collection<Object> c = map.values();
		//9.利用增强for循环输出Collection集合中的元素
		for(Object obj:c) {
			System.out.println(obj);
		}
		
		
	}
}

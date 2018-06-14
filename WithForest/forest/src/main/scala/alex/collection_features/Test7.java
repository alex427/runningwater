package alex.collection_features;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;


/**
 * 26：编写一个类，在main方法中定义一个Map对象（采用泛型）， 加入若干个对象，然后遍历并打印出各元素的key和value。【已完成】
 * 
 * @author JX
 * 
 */
public class Test7 {
	public static void main(String[] args) {
		// 1.定义TreeMap集合,key为Student类型(实现了Comparable接口重写了compareTo方法,按年龄从小到大排序)
		// ,value为String类型表示地址
		Map<Student, String> map = new TreeMap<>();
		map.put(new Student("张三", 20), "北京");
		map.put(new Student("李四", 50), "南京");
		map.put(new Student("王五", 30), "上海");
		map.put(new Student("赵六", 60), "广州");
		map.put(new Student("孙七", 10), "深圳");
		// 1.遍历方式1,采用keySet的迭代器方式
		Set<Student> set = map.keySet();
		Iterator<Student> it = set.iterator();
		while (it.hasNext()) {
			Student key = it.next();
			String value = map.get(key);
			System.out.println(key + "=" + value);
		}
		// 2.遍历方式2,采用keySet的增强for循环方式
		System.out.println("----------------------");
		for (Student student : map.keySet()) {
			String value = map.get(student);
			System.out.println(student + "=" + value);
		}
		// 3.遍历方式3,采用entrySet的迭代器方式
		System.out.println("----------------------");
		Set<Entry<Student, String>> entrySet = map.entrySet();
		Iterator<Entry<Student, String>> it2 = entrySet.iterator();
		while (it2.hasNext()) {
			Entry<Student, String> entry = it2.next();
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
		// 4.遍历方式4,采用entrySet的增强for循环方式
		System.out.println("----------------------");
		for(Entry<Student, String> entry : map.entrySet()) {
			System.out.println(entry.getKey()+"="+entry.getValue());
		}
	}
}

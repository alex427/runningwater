package alex.collection_features;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;


//1：定义map集合，并自定义对象，对map集合遍历，打出key和value值【已完成】
public class Test1 {
	public static void main(String[] args) {
		// 1.定义HashMap集合,键为Student对象,值为String类型的对象,表示地址
		HashMap<Student, String> map = new HashMap<>();
		Student sss = new Student();
		map.put(new Student("张三", 20), "北京");
		map.put(new Student("李四", 20), "南京");
		map.put(new Student("王五", 20), "上海");
		map.put(new Student("赵六", 20), "广州");
		map.put(new Student("孙七", 20), "深圳");

		// 遍历方式一:增强for循环(entrySet)
		for (Entry<Student, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "::" + entry.getValue());
		}
		
		//遍历方式二:增强for循环(keySet)
		System.out.println("-----------------------------------------");
		for(Student student :map.keySet()) {
			String address = map.get(student);
			System.out.println(student+"::"+address);
		}
		
		// 遍历方式三:迭代器(通过entrySet())
		System.out.println("-----------------------------------------");
		Set<Entry<Student, String>> set = map.entrySet();
		Iterator<Entry<Student, String>> it = set.iterator();
		while (it.hasNext()) {
			Entry<Student, String> entry = it.next();
			System.out.println(entry.getKey() + "::" + entry.getValue());
		}

		// 遍历方式四:迭代器(通过keySet())
		System.out.println("-----------------------------------------");
		Set<Student> keySet = map.keySet();
		Iterator<Student> it2 = keySet.iterator();
		while(it2.hasNext()) {
			Student student = it2.next();
			String address = map.get(student);
			System.out.println(student+"::"+address);
		}

	}
}

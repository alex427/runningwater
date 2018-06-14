package alex.collection_features;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * 声明类Student，包含3个成员变量：name、age、score，
 * 创建5个对象装入TreeSet，按照成绩排序输出结果（考虑成绩相同的问题）。
 * @author JX
 *
 */
public class Test9 {
	public static void main(String[] args) {
		TreeSet<Student> set = new TreeSet<>();
		set.add(new Student("张三",30,80));
		set.add(new Student("李四",20,90));
		set.add(new Student("王五",10,70));
		set.add(new Student("赵六",40,90));
		set.add(new Student("六七",60,60));
		//利用迭代器显示set集合的内容
		Iterator<Student> it = set.iterator();
		while(it.hasNext()) {
			Student student = it.next();
			System.out.println(student);
		}
	}
}

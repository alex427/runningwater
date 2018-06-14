package alex.collection_features;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 创建ArrayList对象，添加5个元素，使用Iterator遍历输出【已完成ok】
 * @author JX
 *
 */
public class Test8 {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			String str = it.next();
			System.out.println(str);
		}
	}
}

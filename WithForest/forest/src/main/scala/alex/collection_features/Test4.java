package alex.collection_features;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//编写方法public void deleteNum(ArrayList<String> list){}凡是list成员包含0-9的删除
public class Test4 {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		list.add("abce0fgh");
		list.add("ab2e0fgh");
		list.add("ab3e0fgh");
		list.add("ab5e0fgh");
		list.add("ab6e0fgh");
		list.add("ab5e0f8gh");
		list.add("000234ab5e0f8g9h");
		list.add("abc");
		list.add("def");
		list.add("ghi");
		deleteNum3(list);
		deleteAbc(list);
		System.out.println(list);
	}

	// 利用遍历的方式判断是否包含0-9的字符串
	private static void deleteNum1(List<String> list) {
		// 1.遍历集合
		Iterator<String> it = list.iterator();// 获取迭代器
		while (it.hasNext()) {// it.remove();
			String str = (String) it.next();// str.contains(String str)
			for (int i = 0; i <= 9; i++) {// 0-9的数字
				// 2.判断字符串中是否包含0-9的字符串
				if (str.contains(i + "")) {
					it.remove();
					break;
				}
			}
		}
	}

	// 利用正则表达式
	private static void deleteNum2(List<String> list) {
		// 1.遍历集合
		Iterator<String> it = list.iterator();// 获取迭代器
		// 2.定义正则表达式
		String regex = "\\w*\\d+\\w*";
		while (it.hasNext()) {// it.remove();
			String str = it.next();// str.contains(String str)
			// 3.用正则去匹配
			if (str.matches(regex)) {
				// 4.删除匹配的字符串
				it.remove();
			}
		}
	}

	// 利用正则表达式方法2
	private static void deleteNum3(List<String> list) {
		// 1.遍历集合
		Iterator<String> it = list.iterator();// 获取迭代器
		// 2.定义正则表达式
		String regex = "\\d+";
		Pattern p = Pattern.compile(regex);
		while (it.hasNext()) {// it.remove();
			String str = (String) it.next();// str.contains(String str)
			// 3.获取匹配器对象
			Matcher m = p.matcher(str);
			// 4.用匹配器对象去寻找
			if (m.find()) {
				// 5.删除匹配的字符串
				it.remove();
			}
		}
	}

	// 删除ArrayList集合中的"abc"字符串
	private static void deleteAbc(List<String> list) {
		//1.获取迭代器对象，并进行遍历
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			String str = (String)it.next();
			//2.判断集合中的元素是否等于"abc",如果相等，就删除
			if("abc".equals(str)) {
				it.remove();
			}
		}
	}
}

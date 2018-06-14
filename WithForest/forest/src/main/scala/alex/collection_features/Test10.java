package alex.collection_features;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Test10 {
	public static void main(String[] args) throws Exception {
		// 用反射跳过泛型，调用 ArrayList类中的add方法添加string
		ArrayList<Integer> list = new ArrayList<Integer>();// 定义Integer泛型
		Method m = list.getClass().getMethod("add", Object.class);// 得到add方法对象
		m.invoke(list, "this is a String");// 通过反射来执行list的第一个方法,第一个是list对象,代表该对象的方法,第二个是方法参数
		System.out.println(list.get(0));

	}

}

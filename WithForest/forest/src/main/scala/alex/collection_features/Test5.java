package alex.collection_features;

import java.util.Map;
import java.util.TreeMap;

/**
 * 24：取出一个字符串中字母出现的次数。如：字符串："abcdekka27qoq" ，
 * 输出格式为：a(2)b(1)k(2)...【已完成ok】
 * @author JX
 *
 */
public class Test5 {
	public static void main(String[] args) {
		//1.定义字符串
		String str = "abcdekka27qoq";
		//2.调用getCharNum方法统计每个字符出现的次数,并返回一个新的字符串a(2)b(1)k(2)...
		String newStr = getCharNums(str);
		//3.打印字符串
		System.out.println(newStr);
	}

	private static String getCharNums(String str) {
		//1.将字符串变成字符数组
		char[] chars = str.toCharArray();
		//2.定义集合TreeMap
		TreeMap<Character,Integer> map = new TreeMap<>();
		//3.遍历字符数组,将对应的字符和次数存入map集合中
		for (char c : chars) {
			map.put(c, map.get(c)!=null?map.get(c)+1:1);//三元运算符统计每个字符的个数
		}
		//4.定义StringBuilder
		StringBuilder sb = new StringBuilder();
		//5.遍历map集合,利用StringBuilder将key-value转成a(2)b(1)...格式
		for(Map.Entry<Character,Integer> entry:map.entrySet()) {
			sb.append(entry.getKey()).append("(").append(entry.getValue()).append(")");
		}
		//6.返回StringBuilder的字符串表现形式
		return sb.toString();
	}
}

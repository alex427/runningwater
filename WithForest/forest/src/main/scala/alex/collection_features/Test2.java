package alex.collection_features;

import java.util.Map;
import java.util.TreeMap;

//5：Integer泛型Map中存储字符串【已完成 ok】
public class Test2 {
	public static void main(String[] args) {
		TreeMap<Integer,String> tm = new TreeMap<>();
		tm.put(1, "abc");
		tm.put(2, "def");
		tm.put(3, "ghi");
		tm.put(4, "jkl");
		for(Map.Entry<Integer,String> entry :tm.entrySet()) {
			Integer key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key+"::" + value);
		}
	}
}

package zhiguang.datastructure.staticlinear;

import java.util.List;

public class Test {

	public static void main(String[] args) {
		/*String[]  arr = new String[3];
		System.out.println(arr[arr.length-1]);
		arr[1] = "a";
		System.out.println(Arrays.toString(arr));*/
		List list = null;
		ListArray arr = new ListArray(5);
		arr.add(3, 0);	
		arr.add(0,1);
		arr.remove(0);
		arr.clear();
		System.out.println(arr.getLen());
	}

}

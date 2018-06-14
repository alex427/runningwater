package alex.digui;

import java.util.ArrayList;

public class JosfRing {

	/**
	 * @param args
	 * 约瑟夫环
	 */
	public static void main(String[] args) {
		//调用
		System.out.println(getTheQ(8));
	}

	/*
	 * 1,返回值类型int
	 * 2,参数列表int num
	 */
	public static int getTheQ(int num) {
		ArrayList<Integer> list = new ArrayList<>();		//创建集合, 存储这些人
		for(int i = 1; i <= num; i++) {
			list.add(i);
		}
		
		int count = 1;										//用来数数的,只要是3的倍数就杀人
		for(int i = 0; list.size() != 1; i++) {				//只要集合中人数超过1,就要不断的杀
			if(i == list.size()) {							//如果i增长到集合最大的索引+1时
				i = 0;										//重新归零
			}
			
			if(count % 3 == 0) {							//如果是3的倍数
				list.remove(i--);								//就杀人
			}
			count++;
		}
		
		return list.get(0);
	}
}

package alex.primitive_features;

class Demo5_Char {
	public static void main(String[] args) {
		char c = 'a';
		System.out.println(c);  // write(String.valueOf(c));  new String(data, true);
		char c1 = 97;		    //0 - 65535
		System.out.println(c1);

		byte b = 100;


		char c2 = 3;
		char c3 = 4;
		char c4 = 5;
		char c5 = 6;

		System.out.println(c2);
		System.out.println(c3);
		System.out.println(c4);
		System.out.println(c5);

		//char类型是否可以存储中文,  可以
		char c6 = '中';
		System.out.println(c6);  //中
	}
}

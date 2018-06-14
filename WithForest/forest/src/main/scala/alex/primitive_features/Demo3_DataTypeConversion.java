package alex.primitive_features;

class Demo3_DataTypeConversion {					//Conversion转换
	public static void main(String[] args) {
		//数据类型转换之隐式转换

		int x = 3;
		byte b = 4;
		x = x + b;
		System.out.println(x);

		//数据类型转换之强制转换
		int x1 = 3;
		byte b1 = 4;

		b1 = (byte)(x1 + b1);
		System.out.println(b1);

		//00000000 00000000 00000000 10000010			130的二进制
		//10000010										-126补码
		//00000001										-1求反码
		//10000001										-126反码
		//11111110										-126原码
		byte b2 = (byte)(126 + 4);
		System.out.println(b2);
		//00000000 00000000 00000001 00101100			300的二进制
		//00101100
		byte b3 = (byte)300;
		System.out.println(b3);
	}
}

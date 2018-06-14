package alex.digui;

public class FibonacciRabbit {

	/**
	 * * 不死神兔
	* 故事得从西元1202年说起，话说有一位意大利青年，名叫斐波那契。
	* 在他的一部著作中提出了一个有趣的问题：假设一对刚出生的小兔一个月后就能长成大兔，再过一个月就能生下一对小兔，并且此后每个月都生一对小兔，一年内没有发生死亡，
	* 问：一对刚出生的兔子，一年内繁殖成多少对兔子?
	* 1 1 2 3 5 8 13 21
	* 1 = fun(1)
	* 1 = fun(2)
	* 2 = fun(1) + fun(2)
	* 3 = fun(2) + fun(3)
     * 斐波那契数列的特点很明显: 前两项之和等于第三项
	 */
	public static void main(String[] args) {
		//demo1();
		System.out.println(getFibonacci(12));
	}

	public static int getFibonacci(int base){
	    //递归边界是1和2
	    if(base ==1 | base ==2){
	        return 1;
        } else {
	        return getFibonacci(base-1)+getFibonacci(base-2);
        }
    }

    //如果用数组来做, 那么基本思想就: 1. 你需要一个数组, 数组长度就是你的base; 2. 你有两个确定的数, 第一个和第二个, 它们都是1; 3. 前两项之和等于第三项

	public static void demo1() {
		//用数组做不死神兔
		int[] arr = new int[8];
		//数组中第一个元素和第二个元素都为1
		arr[0] = 1;
		arr[1] = 1;
		//遍历数组对其他元素赋值
		for(int i = 2; i < arr.length; i++) {
			arr[i] = arr[i - 2] + arr[i - 1];
		}
		//如何获取最后一个数
		System.out.println(arr[arr.length - 1]);
	}

	/*
	 * 用递归求斐波那契数列
	 */
	public static int fun(int num) {
		if(num == 1 || num == 2) {
			return 1;
		}else {
			return fun(num - 2) + fun(num - 1);
		}
	}
}

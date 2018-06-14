package alex.primitive_features;

public class Prime {
    //100：2-99,101-200
    public static boolean isPrime(int num){
        for (int i = 2; i < num; i++) {//从2到99来寻找
            if ((num % i) == 0) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
    	int count = 0;
        for(int i = 1; i <= 100; i++) {
            if(isPrime(i)){
            	count++;
                System.out.print(i + " ");
            }
        }
        System.out.println("1-100的素数的个数为:"+count);
    }
}
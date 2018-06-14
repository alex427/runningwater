package alex.digui;

import java.math.BigInteger;

/**
 * 15: 求1000！的结果中包含多少个0？注：1000! = 1×2×3×4×5×...×999×1000
 *
 * @author JX
 */
public class JieCheng {

    public static void main(String[] args) {
        
        char cc =0;
        System.out.println(cc);
        
        String res = factotial(1000).toString();
        System.out.println(res);
        //不能用toCharArray
        String[] arr = res.split("");
        int count =0;
        for(String c: arr){
            if( c.equals("0")){
                System.out.println(c);
                count+=1;
            }
        }
        System.out.println(count);

    }

    public static BigInteger factotial(int a) {
        if (a <= 1) {
            return BigInteger.ONE;
        } else {
            return factotial(a - 1).multiply(BigInteger.valueOf(a));
        }
    }

}

package alex.primitive_features;

/**
 * Created by zhiguang on 2018/4/27.
 */
public class PrimitiveTest {
    public static void main(String[] args){

        Byte b1 = 1;
        char c1 = 1;
        short s1 = 1;
        int i1 = 1;
        int dd = (int)b1;
        int ddd = (int)c1;
        int dddd = (int)s1;

        //Byte bb = (Byte)i1;
        char cc = (char)i1;
        short ss = (short)i1;


        String a = "abc";
        String b = "abc";
        System.out.println(a==b);
        System.out.println(a.equals(b));

        Integer a4 = 50;
        Integer b4 = 50;
        System.out.println(a4==b4);  //true
        System.out.println(a4.equals(b4));  //true

        Integer a5 = 150;
        Integer b5 = 150;
        System.out.println(a5==b5);  //false
        System.out.println(a5.equals(b5));  //true

        Integer aa = new Integer(100);
        Integer ab = new Integer(100);
        System.out.println(aa==ab);  //false
        System.out.println(aa.equals(ab)); //true

        Integer aaa = new Integer(1000);
        Integer aab = new Integer(1000);
        System.out.println(aaa==aab);  //false
        System.out.println(aaa.equals(aab)); //true

    }
}

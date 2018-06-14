package alex.learn.unicodeset;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class WordParser {
    //字符集解码器
    public static void main(String [] args) throws Exception {
        int data = 7;
        String binaryStr = Integer.toBinaryString(data);
        System.out.println("the result is : " + binaryStr); //  111

        byte results[] = binaryStr.getBytes("utf8");
        for(int i = 0;i < results.length ; i++){
            System.out.println("the " + i +  " result is : " + results[i]);//"1"的ascii码是49。
        }

        //获取字符的UTF-8的二进制表达式, 字母, 汉字都能用
        String str1 = new String("李");
        byte[] utf8s = str1.getBytes("utf8");
        System.out.println(utf8s.length);
        StringBuffer binStr = new StringBuffer("");
        for(byte b : utf8s){
            String str2 = Integer.toBinaryString((int)b);
            binStr.append( str2.substring(24));
            binStr.append(" ");
        }
        System.out.println(binStr.toString());
    }
}

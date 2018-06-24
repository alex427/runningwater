package alex.digui;

import java.util.Random;

/**
 * Created by zhiguang on 2018/6/23.
 */
public class DuiguiShuxue {

    public static void main(String[] args){
        Random r = new Random();
        r.nextInt(100);
        for(int a=1;a<101;a++){
            for(int b=1;b<101;b++){
                jisuan(a,b);
            }
        }
    }

    public static void jisuan(int x,int y){
        //  a2=a1+1/4=a+1/4, a3=(1/2)a2 = (1/2)a+ 1/8
        //  x+y=10, x*Y=24
        int a=x+y;
        int b=x*y;
        if(a==10 && b==24){
            System.out.println("x="+x);
            System.out.println("y="+y);
        }

    }

}
